# AFtheM

AFtheM is an HTTP API Microgateway that acts are a reverse proxy.

The pillars of the project are:

* **Modularity**: the system should be expandable by the creation of modules. The activation of modules should not
  require recompilation of repackaging the software. The act of the creation of modules should require the very little
  knowledge of the inner workings of the software, according to our *No Esoteric Bullshit* policy.

* **Customization**: the user should be able to create various pipelines by connecting different steps from
  different modules, in order to achieve a certain goal without special boundaries dictated by the modules. If it's not
  illogical, it should be possible.

* **Fine tuning**: the system performance and resource usage should be fine-tunable by the user, according to their
  needs and knowledge of how the APIs they're proxying work.

* **With developers in mind**: the development process should always consider the ability to capture, debug and
  transform APIs the *main goal* of the project. The tool should be a valuable companion in the process of identifying
  flaws and weaknesses.
  
## The Stack

The AFtheM Microgateway is entirely written in **Scala/Akka**, and requires **Java JRE 8**. The technology serving the
inbound requests is **Apache Tomcat** via **Spring Boot**. The outbound requests are performed by the
**Apache Async Http Client**. 

The modules can be written in either Scala or Java.

## Conventions

* **Upstream** is a URL to the original API
* **Backend** is a configuration that logically connect the characteristics of an inbound request to the *upstream*
* **Flow** describes a pipeline of actions happening between the inbound request and the *upstream*
* **Actor** every action in a flow is called an actor, mainly because it's implemented as an Akka Actor
* **Sidecar** a special type of Actor that will not alter the course of the flow or the content message, and is executed
  in parallel 
* **Probe**: a special object that is meant to verify whether a certain upstram is available or not, according to a
  number of preconditions
* **Durations**: most configuration keys needing to express a certain duration in time can leverage the *duration notation*,
  a human readable way to express time, as in *10 seconds*, *1 minute*, *100 milliseconds*
 