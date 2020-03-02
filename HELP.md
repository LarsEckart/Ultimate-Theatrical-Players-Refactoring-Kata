# Help

Dear friend,

our senior developers who wrote this application have gone to new projects where they can start using kotlin, we need your help!

It's the end of the month, we want to invoice all our clients.
At midnight a cron job will trigger and the invoicing system will make requests to this application.
With the returned data, we'll print out the real invoices and send them to our customers.
Something is wrong though, our latest test runs don't show all performances for the clients.
The invoice for Alibabe is correct, but the invoice for BigCo is missing the last 2 performances we played for them.
And actually, for all other companies the invoices also are too low.
We'll lose money of we don't get those invoices corrected.
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

The list of plays which we can perform is stored in some database.
We're a small theatre, we only have a few plays in our schedule and the office people know how to update the sql script, so don't worry about this.
Since the developers couldn't figure out how to read the excel file with java, we stopped using excel.
Instead, the application reads now some text file which the intern is updating through the repository's web ui.
In the beginning, we only had a few performances and sorted them by company name.
But now we're so popular that we cannot continue doing this and the latest performances simply get added at the end.
That's how we want it, and going back to a sorted text file is no option anymore, don't even think about proposing this.
The sorting approach always seemed so tedious but the developers said this is the only possible way.
I also overheard them talking about some cool stream features in Java 9, no idea what that was about though.






good luck.

