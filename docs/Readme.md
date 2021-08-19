# Considerations
- "What assumptions did you make?"
> At first time that I did read the assignment, I supposed the challenge was really complicated,
because of AJAX in the test description, and SLA less than 10 Seconds<code>(A lot of time to a request)</code>, with that in mind I supposed the test would be hard to finish.
<br/>After understand correctly the Amazon API, I saw all my answers in that, I did many request using Amazon.com, with that I could see a perfect solution to assignment.

- 1.B "How does your algorithm work?"
> If I could say in a few words is, <code>Levenshtein Algorithm</code>. I created a microservice Rest, this one have an endpoint that receive a keyword,
and using Amazon API completion and Levenshtein Algorithm, I can tell how hot is the keyword. Because of performance requirement,
I used Spring Webflux, with so many requests in simultaneous the API is so much faster than Spring Boot / MVC.

- 1.C "Do you think the ( *hint ) that we gave you earlier is correct and if so - why?"
> Yes, it is correctly, because the api just give to you a list of matches, but the order mean nothing.
The tip is right, but was unhelpful, because when I understand the Amazon API Completion, I already imagined a valid solution, almost immediately.

- 1.D "How precise do you think your outcome is and why?"
> 90%. <br/>I'm pretty sure the solution is valid, because is a valid algorithm of similarity, and I also tested a lot of situations
in amazon.com, Amazon API and in the microservice as well, and has excellent response time.<br/>
My only concern maybe is about I did not understand correctly the assignment, maybe the proposal was to create my own algorithm, I imagined this situation,
but I think to myself, why I supposed to do something that already exists, and take the chance to be not good enough, probably I would make something like differences between
keyword and the list of completion of amazon.