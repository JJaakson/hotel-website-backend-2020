package ee.taltech.website.c_theory.question12;

public class Security {

    //todo A
    // What is authentication?
    // Answer: Identifying if someone is, what they say they are. For example login, with username/email and password.

    //todo B
    // What is authorization?
    // Answer: Giving a user permission to access or do something based on their given status. For example
    // logged in users can access an article on some news website and the publisher/admins can change its content.

    //todo C
    // For web (HTTP backend) Spring Security is configured as a ... ? Explain it.
    // Think or read how Spring Security checks are injection into your application.
    // Configured as (one word): Filter.
    // Description: It is based on the Servlet filters. When a request is sent to the servlet, it will pass through a
    // chain of filters, based on the request url. The filters will decide if the request is worth sending through and
    // if it has access. Spring Security is installed as a single filter, as a FilterChainProxy. The spring security
    // filter itself consists of multiple filters, which can created and customized by the user.

}
