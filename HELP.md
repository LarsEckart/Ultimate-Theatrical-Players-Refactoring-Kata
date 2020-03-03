# Help

Hello,

the developers who wrote this application have gone to new projects where they can start using kotlin, so we need your help!

It's the end of the month, we want to invoice all our clients.
At midnight a cron job will trigger and this application will receive requests to generate the invoice texts.
With the returned data, we'll print out the real invoices and send them to our customers.
This application is mission critical and if it is not working correctly, we lose money which we cannot afford to happen.
 
Let me tell you what I remember from the developers:

The list of plays which we can perform is stored in some in-memory database.
We're a small theatre, we only have a few plays in our schedule and the office people know how to update the sql script.
Your time and our budget is limited, so don't worry about this.
Since the developers couldn't figure out how to read the excel file with java, we stopped using excel.
Instead, the application reads a text file which our intern is updating through the repository's web ui.
In the beginning, we only had a few performances and sorted them by company name as we were told.
The sorting approach always seemed so tedious but the developers said this is the only possible way.
I also overheard them talking about some cool stream features in Java 9, no idea what that was about though.
But now we're so popular that we cannot continue doing this and the latest performances simply should get added at the end.
That's how we want it and is your first feature to get working.
Currently it doesn't work, the performances we added at the end don't show up on the invoice texts.

Once you get that fixed and we can invoice all our money, we've budget for the following tasks:

* We want an additional endpoint which returns invoices as html. 
    Here an example:
    ```html
    <h1>Statement for BigCo</h1>
      <table>
        <tr>
          <th>play</th>
          <th>seats</th>
          <th>cost</th>
        </tr>
        <tr>
          <td>As You Like It</td>
          <td>$330.00</td>
          <td>10</td>
        </tr>
        <tr>
          <td>Othello</td>
          <td>$400.00</td>
          <td>20</td>
        </tr>
      </table>
      <p>
        Amount owed is <em>$1,075.00</em>
      </p>
      <p>
        You earned <em>5</em> credits
      </p>
    ```

* We want an additional endpoint which returns invoices as json. This has very low priority for now though.
    Here an example:
    ```json
    {
      "company": "BigCo",
      "performances": [{"name": "Hamlet", "amount": "$400.00", "seats":  10},{"name": "Othello", "amount": "$500.00", "seats":  40}],
      "total": "$900.00",
      "credits": 10
    }
    ```

* Soon we'll also want to play our first drama. 
 As I said, we can add that type to the database ourselves, but the charging rules will be different from what we have for comedy&tragedies.
 We'll charge $30_000 and if there are more than 20 visitors, we charge $1000 extra each. 
 If we have even more than 40 visitors, for those we charge $500 extra each.

* We want a simple, clean, maintainable and reliable application whose functionality we can trust.
 But the last consultant mentioned our application is over-engineered and when he saw the tests, his only remark was "oh my god".
 Can you look into the tests and the code in general and make a list of things that could be done simpler or are not necessary?
 Probably related to this, have a look, we gathered some surprising (or shocking?) data from CI system and application logs.
 The first version of our application resulted in a 26mb jar file and started up in 1.6 seconds.
 Now we're at 55mb and 3.3 seconds.
 That consultant had a great vision but was sadly already booked for months ahead so he had to decline our request.
 Are you up for this kind of challenge?


