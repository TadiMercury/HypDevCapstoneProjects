                        #This program makes use of if/elif/else statements to capture user data,
                        #to validate if what the user has entered is correct and provide a calculated
                        # Currency amount based on the entered data.
                        

import math


#Defining global varibles for use in the program.
#(This is not to incur a 'Name Error' error while using these 'particular' variables in if statements.)

is_bond = False
is_investment = False



#Initial menu user interface output and input by user

menu_text = '''Choose either 'investment' or 'bond' from the menu below to proceed.

investment      - to calculate the amount of interest you'll earn on interest

bond            - to calculate the amount you'll have to pay on a home loan.
'''

print(menu_text)
user_choice = input("Input here: ")
user_choice = user_choice.lower()


#User input validation to make sure they input the correct characters:
if user_choice == "bond":
    is_bond = True
elif user_choice == "investment":
    is_investment = True
else:
    print("\nInvalid input. \nChoose only between these two: \t  'bond' or 'investment' ")
    exit()  #Using exit() to terminate program if it exeutes up to line 37


               
                #When the user chooses "Investment", the following will happen.

if is_investment:
        #User input of additional data needed for Investment calculation.
    deposit = float(input("\n Please input the initial amount of money to be deposited into your investment.  i.e '20000.50' \n Enter here: "))
    in_rate = float(input(" \n Input the investment rate percentage number i.e 'xx' \n Enter here: "))
    num_years = int(input(" \n Input the number of years of investment. \n Enter here: "))
    in_type = input(" \n Input type of interest, 'simple' or 'compound' interest. \n 'S' for 'simple' and 'C' for 'compound'  \n Enter here: ")
   
    #Validation of user input of interest by using a nested if/elif/else statement:
    if in_type == "C" or in_type == "c":
        interest = "Compound"
    elif in_type == "S" or in_type == "s":
        interest = "Simple"
    else:
        print("Invalid Choice")
        exit()


    
    #Calculation of interest:
    #Declaration and usage of interest formalae variables to be used

    A = 0
    r = in_rate/100
    P = deposit
    t = num_years

    #Using an if/else statement to calculate the 'Investment' sum based on the variable 'interest'

    if interest == "Simple" :
        A = round((P*(1+r*t)), 2)       #Using round() to convert calculated number to decimal currency notation.
        investment_output = "\n Your Investment is as follows: \n \n Deposit: \t \t \t R{} \n At Interest Rate: \t \t {}% \n Interest Type:\t \t \t {} \n Total Amount: \t \t \t R{} \n".format(deposit,in_rate,interest,A)
        print(investment_output)
    else:
        A = round((P*math.pow((1+r),t)), 2)     #The else statement will calculate the total investment due to compund interest.
        compInvest_output = "\n Your Investment is as follows: \n \n Deposit: \t \t \t R{} \n At Interest Rate: \t \t {}% \n Interest Type:\t \t \t {} \n Total Amount: \t \t \t R{} \n".format(deposit,in_rate,interest,A)
        print(compInvest_output)


                   
                    #When the user chooses "Investment", the following will happen.


#Using a continuation of the if statement on line 23
else:
    if is_bond:
        house_value = float(input("\n Input present value of house i.e '20000':  \n Enter here: "))
        in_rate = float(input("\n Input the investment rate percentage number i.e '16' \n Enter here: "))
        bond_repay = int(input("\n Input number of months planned to repay bond i.e '120' \n Enter here: "))


#Calculating the bond repayment

        #Declaration and usage of bond repayment formala variables to be used
        x = 0
        i = (in_rate/12)
        n = bond_repay
        P = house_value

        #Calculating the bond repayment amount and subsequent output of results

        x =   round( ( (i*P) / (1 - (1 + i) ** (-n)) ) ,2)           #Using round() to convert calculated number to decimal currency notation.
        monthly_Xamount = round((x/bond_repay), 2)                  #Variable monthly_Xamount calculates the monthly amount to be paid from the calculated bond amount.
        

        #Using a long string to output calculated values.
        bond_output = '''                               
         Your Bond Repayment is as follows:

         Initial House value           R{}
         No. months to repay:          {} month(s)
         Interest Rate (Years):        {}%
         Total Repayment Amount:       R{}
         Monthly Repayment Amount      R{}
         '''.format(house_value,bond_repay,in_rate,x,monthly_Xamount)
        print(bond_output )
    else:
        print("Something went wrong, please try again.")


    
    

