# Help

Dear friend,

the developers who wrote this application have gone to new projects where they can start using kotlin, we need your help!

It's the end of the month, we want to invoice all our clients.
At midnight a cron job will trigger and this application will receive requests to generate the invoice texts.
With the returned data, we'll print out the real invoices and send them to our customers.
Something is wrong though, our latest test runs don't show all performances for the clients.
The invoice for Alibabe is correct, but the invoice for BigCo is missing the last 2 performances we played for them.
Actually, for all other companies the invoices are also too low.
We'll lose a lot of money of we don't get those invoices corrected.
I wrote you one expected invoice text for BigCo:

```
Statement for BigCo
  Hamlet: $400.00 (10 seats)
  As You Like It: $500.00 (25 seats)
  Othello: $400.00 (20 seats)
  As You Like It: $620.00 (40 seats)
  As You Like It: $604.00 (38 seats)
Amount owed is $2,524.00
You earned 38 credits
```

Let me tell you what I remember from the developers:

The list of plays which we can perform is stored in some in-memory database.
We're a small theatre, we only have a few plays in our schedule and the office people know how to update the sql script.
Your time and our budget is limited, so don't worry about this.
Since the developers couldn't figure out how to read the excel file with java, we stopped using excel.
Instead, the application reads a text file which our intern is updating through the repository's web ui.
In the beginning, we only had a few performances and sorted them by company name as we were told.
But now we're so popular that we cannot continue doing this and the latest performances simply get added at the end.
That's how we want it, and going back to a sorted text file is no option anymore, don't even think about proposing this.
The sorting approach always seemed so tedious but the developers said this is the only possible way.
I also overheard them talking about some cool stream features in Java 9, no idea what that was about though.
The guy who implemented it seemed kinda reckless.

That's all I can tell you, good luck.

And while we're at it.
Once you get that fixed and we can invoice all our money, we've budget for the following tasks:

* We want an additional endpoint which returns invoices as json. 
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
We'll charge $50000 and if there are more than 20 visitors, we charge $1000 extra each. If we have even more than 40 visitors, for those we charge $500 extra.

thisAmount = 40000;
                    if (perf.audience > 30)
                        thisAmount += 1000 * (perf.audience - 30);
                    break;


* We're a bit concerned. 
The first version of our application resulted in a 26mb jar file, started up in 1.6 seconds, and consumed 62mb of memory.
 Now we're at 53mb, 2.8 seconds and 177mb.
 Any chance you can get us back to a more slim and simple solution?
 



good luck.

