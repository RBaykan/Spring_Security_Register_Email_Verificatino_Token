# Spring Security Register with Email Verification

## Description
This is a continuation of the previous project. [link]
In the previous project, when a user is successfully registered in the database, they are not immediately activated, and an event called OnRegisterComplete is triggered. The relevant user and registration link are registered in the OnRegisterEvent. This event generates a verification token with a validity period and saves it in the database for verification. The token is associated with the corresponding user.

The token information is appended as a parameter to the registration link and sent to the user's registered email address.

When the user clicks on the link, the token information from the link is queried in the database, and the token verification process is carried out. If the token is valid, the user is activated.
