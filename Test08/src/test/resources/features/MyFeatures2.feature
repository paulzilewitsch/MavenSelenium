Feature:  User login on social networking site.

Scenario: 
| username  | password  |
| username1 | password1 |
| username2 | password2 |
| username3 | password3 |

Given user navigates to Facebook
When I enter Username as "<username>" AND Password as "<password>" 
Then login should be unsuccessful