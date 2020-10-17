package ee.taltech.website.a_theory.question5;

public class ApplicationLayers {

    //todo
    // Architects insist on having layered architecture in the back-end... ¯\_(ツ)_/¯

    //todo p1
    // Name 3 layers of back-end architecture. Give a brief description for each.
    // 1: API Layer
    // Description: It presents the  functionality of the application to the end user.
    // 2: Business layer
    // Description: It contains the logic and is responsible for evaluating the data.
    // 3: Data Layer
    // Description:It is responsible for sending and receiving data to the database.

    //todo p2
    // Do you agree with the architects? Why?
    // Yes
    // Because: It makes the whole structure of the application more clear and easier to understand.

    //todo p3
    // We use objects to transport data between different layers.
    // What is the difference between Entity and Dto? What is same between them?
    // Answer: An Entity is defined only for the purpose of storing it in a database.
    // DTO is the class we use for all operations other than database-related ones.
    // DTO and Entity hold the same information.

}
