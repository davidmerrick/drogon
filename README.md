# About

A service that listens to SNS and then breathes fire (notifications) to Alexa. 

I'm currently just using it to notify me when my
[partymode](https://github.com/davidmerrick/partymode) service buzzes people into my building,
but it could easily be extended to support many different kinds of notifications that you want to
push from SNS to your Alexa. 

# Installation

- Install the [Notify Me](https://www.amazon.com/Thomptronics-Notify-Me/dp/B07BB2FYFS) skill.
- Invoke the skill, which will e-mail you an access key.
- In your environment, set the `NOTIFYME_KEY` to that value.
- Run `./gradlew deployPrd` to deploy it.

Test out the integration by going to SNS and publishing a message. If 
everything is configured correctly, you'll see a notification pop up on your Echo.

![Drogon](img/drogon.jpg)


