# PROJECT JOURNAL

## Team Members
1. Janette Lopez Urzua 
2. Karnavee Kamdar
3. Mrunali Sanjay Khandat 
4. Prathyusha Lingamallu

## Component Diagram
![title](https://github.com/gopinathsjsu/team-project-cmpe_202_team_project/blob/master/Diagrams/Banking_system_component_diagram.png)
<br>
![title](https://github.com/gopinathsjsu/team-project-cmpe_202_team_project/blob/master/Diagrams/deployment.JPG)

## Wireframes
![title](https://github.com/gopinathsjsu/team-project-cmpe_202_team_project/blob/master/wireframes/homepage.JPG)
![title](https://github.com/gopinathsjsu/team-project-cmpe_202_team_project/blob/master/wireframes/Signup.JPG)
![title](https://github.com/gopinathsjsu/team-project-cmpe_202_team_project/blob/master/wireframes/Login.JPG)
![title](https://github.com/gopinathsjsu/team-project-cmpe_202_team_project/blob/master/wireframes/addapayee.JPG)
![title](https://github.com/gopinathsjsu/team-project-cmpe_202_team_project/blob/master/wireframes/removepayee.JPG)
![title](https://github.com/gopinathsjsu/team-project-cmpe_202_team_project/blob/master/wireframes/setuprecurringbillpayment.JPG)
![title](https://github.com/gopinathsjsu/team-project-cmpe_202_team_project/blob/master/wireframes/setuponetimebillpayment.JPG)
![title](https://github.com/gopinathsjsu/team-project-cmpe_202_team_project/blob/master/wireframes/setuprecurringtransfer.JPG)
![title](https://github.com/gopinathsjsu/team-project-cmpe_202_team_project/blob/master/wireframes/transfer.JPG)
![title](https://github.com/gopinathsjsu/team-project-cmpe_202_team_project/blob/master/wireframes/createnewaccount.JPG)
![title](https://github.com/gopinathsjsu/team-project-cmpe_202_team_project/blob/master/wireframes/requestsavingaccount.JPG)
![title](https://github.com/gopinathsjsu/team-project-cmpe_202_team_project/blob/master/wireframes/accountdetails.JPG)
![title](https://github.com/gopinathsjsu/team-project-cmpe_202_team_project/blob/master/wireframes/searchviewtransactions.JPG)
![title](https://github.com/gopinathsjsu/team-project-cmpe_202_team_project/blob/master/wireframes/searchviewtransactions2.JPG)
![title](https://github.com/gopinathsjsu/team-project-cmpe_202_team_project/blob/master/wireframes/addmanualrefunds.JPG)
![title](https://github.com/gopinathsjsu/team-project-cmpe_202_team_project/blob/master/wireframes/addcredit.JPG)

## Design Decisions
 - APIs created on Spring Boot Framework. 
     - Programming Language: Java
     - Input and Output: Json
     - Added Validation and error handling
 - Database used: MySQL
 - For transactions part, transfer between accounts, recurring transfer, recurring bill payment, manual refunds by the admin, adding credit would need either to deposit or withdraw money from saving or checking account to complete transaction
 - An interface called "AddTransactionCustomInterface" was created so that all the functionalities that perform transactions would be performing transactions using this interface.
 - Two more additional interfaces- "SavingAccountCustomInterface" and "CheckingAccountCustomInterface" were created to perform deposit and withdraw functions for transacting through the accounts.


## Weekly Scrum Report
### Janette 
Meeting Date | What tasks did I work on / complete? |  What am I planning to work on next? | What tasks are blocked? 
-------------|--------------------------------------|--------------------------------------|------------------------
October 26   | Understand Requirements              | Create Meeting Minute Documents      | None
November 2   | Create Meeting Minute Documents      | Set Up a MySQL database on an EC2    | None
November 9   | Setting Up Database                  | Learn Spring Boot                    | None
November 20  | Learn Spring Boot                    | Add, get, and delete user            | None
November 23  |  Add, get, and delete user           | Check due balances, Check balance, close existing account. Finish meeting minutes docs. Create wireframes. Do extra credit. | All APIs I am planning on work on next are blocked. 'Creating accounts' needs to be finished first. I can't work on the extra credit until the APIs are done.
December 4   | Check due balances, Check balance, close existing account. | Few days to work on the wireframes and extra credit | Extra credit can't get started until everything else that is required is finished.    

### Karnavee
Meeting Date | What tasks did I work on / complete? |  What am I planning to work on next? | What tasks are blocked? 
-------------|--------------------------------------|--------------------------------------|------------------------
October 26   | Understand Requirements              |                                      | None
November 2   |                                      |                                      | None
November 9   |                                      |                                      | None
November 20  |                                      |                                      | None
November 23  |                                      |                                      |
December 4   |                                      |                                      |

### Mrunali
Meeting Date | What tasks did I work on / complete? |  What am I planning to work on next? | What tasks are blocked? 
-------------|--------------------------------------|--------------------------------------|------------------------
October 26   | Understand Requirements              | Decide the technology stack          | None
November 2   | Decided the technology stack as Spring Boot, MySQL database, IDE- STS/IntelliJ                | Design database schema                    | Product backlog
November 9   | Designed database schema, product backlog  | Learn Spring Boot- use of tables with Spring Boot, knowledge of REST APIs     | None
November 20  | Learnt Spring boot, REST APIs, connecting MySQL to application | Update user  | Add user functionality
November 23  | Updated user/customer's phone number, email, mailing address  | Adding manual transactions, credit  |  Transactions table, account creation
December 4   | Added manual transactions, credit, approve/deny transactions, implemented deposit and withdraw functions that form basis for all transactions, recurring transfer  | Adding validations, component diagram |

### Prathyusha
Meeting Date | What tasks did I work on / complete? |  What am I planning to work on next? | What tasks are blocked? 
-------------|--------------------------------------|--------------------------------------|------------------------
October 26   | Understand Requirements              |Design Datbase Schema/Product Backlog | None
November 2   | Design Database schema               |     Learn Spring boot                | None
November 9   | Learnt Spring boot                   |     ADD Payee/Remove Payee           | None
November 20  | Add Payee/Remove Payee               |      Approve Payee                   | None
November 23  |          Approve Payee               |    One time/Recurring bill payment   | Transactions API
December 4   | One time bills/recurring bills       | Validations,UI Wireframes/Burndown   | None

## XP Core Values

### Janette 
Meeting Date | XP Core Value  |  Explanation
-------------|----------------|-------------
October 26   | Communication  | First time we met and everyone encouraged communication by discussing what the project was about and what we needed to do next.  
November 2   | Communication  | We discussed various programming languages and frameworks to use in this project. Everyone was participating and giving their own opinion.
November 9   | Communication  | During this meeting we worked on the backlog and agreed on how many hours each task would take. Everyone participated.
November 20  | Feedback       | We met today to check how everything was going with the project. We all gave our feedback about each others contributions so far and made changes according to it.
November 23  | Simplicity     | We kept this meeting simple. We met online for a short time and divided tasks before the long break. 
December 4   | Communication  | The team got together and discussed solutions for some problems in the code. We helped each other a lot by communicationg since we all came up with solutions.

### Karnavee
Meeting Date | XP Core Value  |  Explanation
-------------|----------------|-------------
October 26   |                | 
November 2   |                | 
November 9   |                | 
November 20  |                | 
November 23  |                |
December 4   |                |

### Mrunali 
Meeting Date | XP Core Value  |  Explanation
-------------|----------------|-------------
October 26   | Communication  | As a champion of communication value, I encouraged communication in the team by arranging standup meetings every week
November 2   | Communication  | Asked my team members to share their take on various available frameworks 
November 9   | Communication  | Engaged my team members in conversation about the database schema, product backlog
November 20  | Communication  | Encouraged everyone to ask doubts to each other, asking how a functionality is implemented 
November 23  | Communication  | Engaged my team members in conversation about their current work, what they're planning to work on next 
December 4   | Communication  | Sharing the implementation helped in reducing workload of individual team member

### Prathyusha 
Meeting Date | XP Core Value  |  Explanation
-------------|----------------|-------------
October 26   | Communication  | we communicated face to face regularly.
November 2   | Communication  | We Created Backlog effectively by discussing various aspects of the project
November 9   |  Simplicity    | We designed what was required for the project and no more
November 20  |   Feedback     | We Demonstrated and listend carefully and made any changes needed. We talked about the project and adapt our process to it, not the other way around.
November 23  | Communication  |  We worked together on everything from requirements to code. We created the best solution to our problem that we can together.
December 4   | Communication  | We sat together and implemented the pending backlogs by bringing them to none

