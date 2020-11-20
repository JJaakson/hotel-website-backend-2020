**Inital setup:**
-    We are using AWS for our Ubuntu 20.04 virtual server and the instace type is t2.micro.

**Connecting:**
        We are using Putty to connect to the server, since we are using Windows 10.
    
- For Windows(Using Putty):
        
    - Open Putty, insert into Host Name box: ubuntu@ec2-13-59-14-54.us-east-2.compute.amazonaws.com

    - Ensure that Port value is 22 and connection type is SSH;

    - From the Category pane expand Connection, SSH and choos Auth;

    - Browse for your .ppk file and choose Open.

- For Linux(Using SSH):
    - ssh -i /path/your-key-pair ubuntu@ec2-13-59-14-54.us-east-2.compute.amazonaws.com

If the server asks for passphrase, then it is: hoteltrivago.

First thing I did when I connected to the server was, I edited authorized_keys and put there
our teams public keys and all of the mentors public keys.

**Setup:**
    
- **Virtual memory:**
        Since angular is memory hungry when it is run, we needed to add virtual memory.
        Running htop will open up a view similar to Windows Task Manager, it will show all ongoing
        processes.
        
    - To add a swap file:

            sudo fallocate -l 2G /swapfile

            sudo chmod 600 /swapfile

            sudo mkswap /swapfile

            sudo swapon /swapfile

            sudo swapon -show

            echo '/swapfile none swap sw 0 0' | sudo tee -a /etc/fstab


- **Dependencies:**
        Since it is a fresh virtual machine installation, we need to install dependencies and update it.
        
    - To update:

            sudo apt-get update

            sudo apt-get upgrade

            sudo apt-get install openjdk-11-jre openjdk-11-jdk


- **Gitlab runner:**
        We are using Gitlab runner to test, produce and add our back-end and front-end files to the
        server. To do this we need to first install and set up gitlab runner.
    
    - First we install the runner insert into terminal on your machine:

            curl -LJO https://gitlab-runner-downloads.s3.amazonaws.com/latest/deb/gitlab-runner_amd64.deb

            dpkg -i gitlab-runner_amd64.deb

    - Second go to Settings -> CI/CD -> Runners, find your Runner setup url and the token,
        you need these to register your runner. Insert into terminal:

            sudo gitlab-runner register

        It will ask for projects runner url and token. Tags are not crucial but they help to keep
        an eye on the project when there are multiple tasks/projects running. And for the executor
        choose **shell**.

    - To start the gitlab runner and to see the status insert:

            sudo service gitlab-runner start

            sudo service gitlab-runner status

            (sudo service gitlab-runner restart)

Now it is needed to configure CI in gitlab and to do that the project has to have
.gitlab-ci.yml file in projects root. This will build and test your project depending on how
you configure it official guide https://docs.gitlab.com/ee/ci/yaml/ , but we used the given
example and modified it.

Every time when the project pushed and this file is in the prject root and the runner is running
it will execute pipeline and its configured jobs and if everything is done correctly the jobs will
succeed and produce the builds on the servers runner. (For this project, both repositories
back- and front-end have to be registered)
For our project, we had a few set backs but everything went fine.


- **Testing(For backend jar file):**
        If the deploy stage passed, it created a runnable jar file on our server. 
    - To test and try torun it on our server go:

            sudo su gitlab-runner

        This will log you in as gitlab-runner.

    - Go to the correct directory, this will be depending on how deploy is configured, for our project
        it is in the folder api-deployment:

            cd /home/gitlab-runner/

            cd api-deployment/

            ls

    - And to run it (for us the jar file name is: hotel-trivago-backend.jar):

            java -jar jarfile.jar

If everything went well, it should start without anyproblems. We setup Swagger to test our api
so if it started on the server, we can go to http://13.59.14.54:8080/api/swagger-ui/ to see if
everything is running fine.


- **Back-end configuration:**
        To make the api run in the background we need to create a service that the server will run.
        For that to happen login as user ubuntu(if logged in as gitlab-runner insert: exit). And we
        need to create a service. 
    - To create a service file (for this project rooms.service):

            cd /etc/systemd/system/

            sudo touch rooms.service

    - Insert the following files in to the file:

            [Unit]
            Description=dashboard rooms service
            After=network.target

            [Service]
            Type=simple
            User=gitlab-runner
            WorkingDirectory=/home/gitlab-runner/api-deployment
            ExecStart=/usr/bin/java -jar hotel-trivago-backend.jar
            Restart=on-abort

            [Install]
            WantedBy=multi-user.target

        Exit and save it. 
    - The configuration must be reloaded, process enabled and service started:

            sudo systemctl daemon-reload

            sudo systemctl enable rooms

            sudo service rooms restart

            (sudo service rooms status)

    - Now we need to allow gitlab runner to restart it:

            sudo visudo
            
    - Add the following line to the end:

            gitlab-runner ALL = NOPASSWD: /usr/sbin/service heroes *

If everything went as planned, jar file should be running. http://13.59.14.54:8080/api/swagger-ui/


- **Front-end configuration:**
   
    - Install node:

            sudo curl -sL https://deb.nodesource.com/setup_12.x | sudo -E bash -

            sudo apt-get install y- nodejs

    - Install nginx:

            sudo apt-get install nginx

        Nginx creates a default config that won't match the project, so it is needed to create and replace
        with our projects config. 

    - To create a new config file:

            cd /etc/nginx/sites-available

            sudo cp default rooms

    - Now to create a symlink to /sites-enabled and remove default symlink:

            cd /etc/nginx/sites-enabled

            sudo ln -s /etc/nginx/sites-available/rooms /etc/nginx/sites-enabled/

            sudo rm default

    - To show our frontend we need to create a symlink to front-ent code:

            cd /var/www/

            sudo ln -s /home/gitlab-runner/front-deployment/ /var/www/front-deployment/

    - Now we need to edit the file in sites-available and add necessary code:
        - Backend proxy, this will not need you to enter the port address when accessing it from other
            devices:

                location /api/ {
                        proxy_pass   http://localhost:8000;
                    }

        - To show our frontend, replace root to point to:

                root   /var/www/front-deployment;

        - Frontend urls break, so that when we refresh the website, it will not run into and error:

                 location / {
                         index  index.html index.htm;
                         if (!-e $request_filename){
                           rewrite ^(.*)$ /index.html break;
                         }
                     }
                     
    - Now restart it:

            sudo service nginx restart

**And http://13.59.14.54 should display front-end.**

