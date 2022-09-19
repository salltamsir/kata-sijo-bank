Think of your personal bank account experience When in doubt, go for the simplest solution Requirements

·         Deposit and Withdrawal

·         Account statement (date, amount, balance)

·         Statement printing

 User Stories

·         US 1:

In order to save money

As a bank client

I want to make a deposit in my account

·         US 2:

In order to retrieve some or all of my savings

As a bank client

I want to make a withdrawal from my account

·         US 3:

In order to check my operations

As a bank client

I want to see the history (operation, date, amount, balance) of my operations

---

My Solution :

My current solution implements withdraw, deposit, balance and history usecases.

In case of error (non-existent account, insufficient balance), an exception is thrown with a custom message.

A database access can by added by implementing the DAO interfaces. This part is mocked in the unit tests.

---
Improvements to be made : 

Detect other possible functional errors and handle each one in a personalized way.

Add a functionality to allow to filter the history by date, operation type, amount, location etc...

Etc...



