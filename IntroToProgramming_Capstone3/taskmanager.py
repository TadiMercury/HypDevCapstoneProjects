#       This a program for a small company that will handle its tasks to a specific user
#       It compromises of login using a username and password; with authentication.
#       It showcases a main menu that allows a user to choose different options on how to manage their tasks.



###########################################START OF CODE#########################################################################



#Files
user_file = open("user.txt","r")
task_file = open("tasks.txt","r")


#File manipulation
user_list = user_file.readlines()
task_list = task_file.readlines()

user_file.close
task_file.close



#Initial variable strings to output to the user.
start_message = '''
Welcome to the Task Manager Program
'''

print(start_message)
login_message = '''
Please start by adding your username and password to login.
Press any key to continue...
'''
input(login_message)

#String variables for username and password
username = input("\nPlease enter your username here: \n")
username = username.lower()
print("\n")
access = False

#looping for validation

for line in user_list: # Read the lines
    login_info = line.split(", ") # Split on the space, and store the results in a list of two strings
    print(login_info)
    while True :
        if username == login_info[0]:
            password = input("\nEnter your password: \n ")
            break
            
        else:
            username = input("Invalid Username. \t \t ReEnter Username again: \n ")
            
    
    while True:
        if password == (login_info[1]).strip("\n"):
            print("\nAccess Granted. Welcome! \n")
            access = True
            break

        else:
            password = input("Invalid Password. \t \t ReEnter your password: \n ")
    if access:
        break
            


#String variables to output to use showing main menu.

main_menu = '''
Please select one of the following options:

r   -       register user
a   -       add task
va  -       view all tasks
vm  -       view my tasks
e   -       exit
'''


admin_mainMenu = '''
Please select one of the following options:

r   -       register user
a   -       add task
va  -       view all tasks
vm  -       view my tasks
s   -       View Statistics
e   -       exit
'''


menu_choice = ""
if username == "admin":
    print(admin_mainMenu)
    menu_choice = (input("Input here: ")).lower()
else:
    print(main_menu)
    menu_choice = (input("Input here: ")).lower()


#Declaration of bool variables
isUser_reg = False
all_tasks = False
task_add = False
my_tasks = False
is_stats = False



#If statements that set boolean values to true when requested by user.

while True:

    if menu_choice == "r":
        isUser_reg = True
        print("User reg")
        break


    elif menu_choice == "a":
        task_add = True
        break


    elif menu_choice == "va":
        all_tasks = True
        print("View all tasks")
        break


    elif menu_choice == "vm":
        my_tasks = True
        print("View my task")
        break

    elif menu_choice == "s" and username == "admin":
        is_stats = True
        print("Admin chose stats")
        break


    elif menu_choice == "e":
        print('Exit goodbye')
        exit()

        
    else:
        input('\nInvalid choice, try again \n')
        print(main_menu)
        menu_choice = (input("Input here: ")).lower()






              
                                #New user registration

if isUser_reg:
    if username == "admin" and password == "adm1n":
        print('''
Menu Prompt to Register a New User
You are Rquired to provide:         New Username
                                    New Password
                                    Confirm Password
            
    ''')
        input("Press any key to continue \n")
                #Variables to register a new user
        new_user = input("Input new username: \n")
        new_pass = input("\nInput new password: \n")
        pass_confirm = input("\nReEnter password to confirm: \n")
        
        #Else statement if the user logged on is not 'admin'
    else:
        print("\nUser is not authorised to register a user. Try another option \n")
        exit()

    

            #Iteration for vaidation
    while True:
        if pass_confirm == new_pass:
            print("New password authenticated \n")
            print("\nYou have successfully registered a new user")
            break
        else:
            print("\nPassword is invalid. Please input correct password \n")
            pass_confirm = input("\nReInput Password please: \n")

        
            #Appending to file
    newUser_str = new_user + ", " + new_pass
    append_file = open("user.txt","a+")
    append_file.write("\n" + "".join(newUser_str))
    append_file.close()


            #Else statement if the user logged on is not 'admin'
    






                                        #Adding a new task

if task_add:
        #Output information
    print('''
Menu Prompt to Add A New Task.
You are required to provide:   

                                    Username of Person
                                    Title of the Task
                                    Description of Task
                                    Start Date of Task
                                    Due Date of Task

    ''')
    input("Press any key to continue: \n")

        #User input for data
    new_usernme = input("\nEnter username:\n ")
    title = input("\nInput title of task: \n")
    description = input("\nInput the description of the task: \n")
    date_given = input("\nInput Start Date in format (Day Month Year): \n")
    due_date = input("\nEnter due date: \n")
    is_task = "No"

    string_file = new_usernme + ", " + title + ", " + description + ", " + date_given + ", " + due_date + ", " + is_task
    append_file = open("tasks.txt","a+")
    append_file.write("\n" + "".join(string_file))
    append_file.close()
    





                                        #View all tasks


if all_tasks:
    print('''
Menu Prompt to view All Tasks

''')

    input("Press any key to continue: \n")

        #Displaying all tasks
    print("\nNow displaying all current tasks assigned: \n")
    num = 0

    for i in task_list:

        current_task = i.split(", ")
        tsk_user = current_task[0]
        tsk_description = current_task[2]
        tsk_title = current_task[1]
        tsk_date = current_task[3]
        tsk_dueDate = current_task[4]
        tsk_complete = current_task[5]
        num += 1

        task_display = '''
Task  {}:

    User:                   {}
    Title:                  {}
    Description:            {}
    Date Given:             {}
    Date Due:               {}
    Task Complete?          {}
'''.format((num), tsk_user, tsk_title, tsk_description, tsk_date, tsk_dueDate, tsk_complete)

        print(task_display)







                                    #View tasks assigned to user


if my_tasks:

    print('''
Menu Prompt to show user's current tasks: 
''')

    input("\nPress any key to continue: \n")

    noTask = False
    num = 0
    for i in task_list:

        current_task = i.split(", ")
        tsk_user = current_task[0]
        tsk_description = current_task[2]
        tsk_title = current_task[1]
        tsk_date = current_task[3]
        tsk_dueDate = current_task[4]
        tsk_complete = current_task[5]
        num += 1

        task_display = '''
User's Current Task  :{}

    User:                   {}
    Title:                  {}
    Description:            {}
    Date Given:             {}
    Date Due:               {}
    Task Complete?          {}
'''.format((num), tsk_user, tsk_title, tsk_description, tsk_date, tsk_dueDate, tsk_complete)


        if username == tsk_user:
            print(task_display)
        else:
            noTask = True
    
    if noTask:
        print("\nNo Current tasks for user.\n")








                                    #Admin's Statistics


if is_stats:

    print("\nMenu Prompts to view Program's Statistics \n")

    input("\nPress any key to continue...")

    for user in user_list:
        prog_user = user[0]

    num_users = '''
Total number of users of this program is :              {}
'''.format(len(user_list))

    user_output = '''
                                                        {}
                                                    
'''.format(prog_user)


    num_tasks = '''
The total number of tasks assigned to users:            {}
'''.format(len(task_list))

    print("These are the statistics of this program currently. \n")
    print(num_users)
    print(num_tasks)
    


    

    

        


        



        
