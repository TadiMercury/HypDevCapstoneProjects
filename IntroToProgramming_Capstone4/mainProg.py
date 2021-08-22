                    #This is a program code for a task management system that makes use of different functions 
                    #that do specific things based on the user's choice.




import datetime


                        #Defining functions to use in the main program

#Function code to load data about users and load it to a list
def userfile():
    user_file = open("user.txt","r", encoding='utf-8')
    user_list = user_file.readlines()
    user_file.close
    return user_list

        #Function code to load data to task file and to wite it to a list
def taskfile():
    task_file = open("tasks.txt","r", encoding='utf-8')
    task_list = task_file.readlines()
    task_file.close
    return task_list

    #Function to append new data to the user file
def regUser_file(new_user, new_pass):

    newUser_str = new_user + ", " + new_pass
    append_file = open("user.txt","a+")
    append_file.write("\n" + "".join(newUser_str))
    append_file.close()


    #Function code to append data to task file.
def addTask_file(string_file):
    append_file = open("tasks.txt","a+")
    append_file.write("\n" + "".join(string_file))
    append_file.close()

    #Function code to save indexed task completed data to a list to use in another function.
def Completedtolist():
    filesss =  taskfile()
    myDict = {}

    for line in filesss: # Read the lines and store them in a dict{}
        
        listTask = line.strip("\n").split(", ")
        myDict[listTask[1]] = listTask[5] 

    completedlist = []
    for i in myDict.values():
        completedlist.append(i)
    return completedlist


        #Dict{} that has usernames as the keys and the corresponding passwords as their values
def userDict():
    userlist = userfile()
    userdictionary = {}

    for i in userlist:
        templist = i.split(",")
        userdictionary[templist[0]] = ''
        
    return userdictionary

        #Function to return dictionary with tasks as their keys and due dates as their values
def dateDuetolist():
    filesss =  taskfile()
    myDict = {}

    for line in filesss: # Read the lines and store them in a dict{}
        
        listTask = line.strip("\n").split(", ")
        myDict[listTask[1]] = listTask[4] 

    
    dateduelist = []
    for i in myDict.values():
        dateduelist.append(i)
    return dateduelist

        #Defining function to generate stats from the task file
def generateTaskOverview():
    completed_tasks = 0
    completedlist = Completedtolist()
    uncompleted_tasks = 0
    overdue_tasks = 0
    totaltasks = 0
    tasklist = taskfile()
    dateDueList = dateDuetolist()
    datetimeList = []

    print("\nMenu option to generate Task Overview Report.")
    input("\nPress any key to continue: ")


    with open('task_overview.txt', 'w', encoding='utf-8') as task_overview:

        #Total number of tasks recorded.
        totaltasks = len(tasklist)
        

        #Counting the number of completed and incomplete tasks
        for count in completedlist:
            if count == "Yes":
                completed_tasks += 1
            elif count == "No":
                uncompleted_tasks += 1

        #Percentage of tasks completed
        percentageComplete = round(((completed_tasks/totaltasks) * 100), 2)
        percentageIncomplete = round(((uncompleted_tasks/totaltasks) *100), 2)

        #Changing the date string into a format readable by python.
        for date in dateDueList:
            datetimeList.append(datetime.datetime.strptime(date, "%d %b %Y"))
        

        #Looping through two lists to check if there are any overdue tasks.
        for datetime_object, i in zip(datetimeList, completedlist):
            
            if datetime_object < datetime.datetime.today() and i == "No":
                    overdue_tasks += 1

            

        
        numtasks = "Total number of tasks recorded is {}".format(totaltasks)
        comtasks = "Num of completed tasks: {}".format(completed_tasks)
        comPercenttasks = "Percentage of completed tasks : {}%".format(percentageComplete)
        incomptasks = "Num of uncompleted tasks: {}".format(uncompleted_tasks)
        incompPercent = "Percentage of uncompleted tasks: {}%".format(percentageIncomplete)
        taskOverdue = "Num of Overdue tasks: {}".format(overdue_tasks)

        task_overview.write(numtasks + "\n")
        task_overview.write(comtasks + "\n")
        task_overview.write(comPercenttasks + "\n")
        task_overview.write(incomptasks + "\n")
        task_overview.write(incompPercent + "\n")
        task_overview.write(taskOverdue + "\n")

        print("Successfully created text file.")
        input("\nPress any key to continue to the menu screen:")
        menu_option()

        return task_overview


        #User function to show stats from the user files if they have been generated.
def user_stats():
    print("\nMenu option to display user stats")
    input("Press any key to continue")
    print("\n")

    with open("user_overview.txt", "r", encoding='utf-8') as userRead:
        userStatslist = userRead.readlines()

    if len(userStatslist) == 0:
        print("Oops, looks like there are no stats to display.")
        print("Please generate user statistics using the Generate Statistics Option")
    else:
        for lines in userStatslist:
            print(lines)


        #User function to show stats from the task files if they have been generated.
def task_stats():
    print("\nMenu option to display task stats")
    input("Press any key to continue")
    print("\n")

    with open("task_overview.txt", "r", encoding='utf-8') as userRead:
        userStatslist = userRead.readlines()

    if len(userStatslist) == 0:
        print("Oops, looks like there are no stats to display.")
        print("Please generate user statistics using the Generate Statistics Option")
    else:
        for lines in userStatslist:
            print(lines)
    

        #Function code to generate .txt report file with the user's stats.
def generateuser_overview():
        #Reading from relavant text files
    userlist = userfile()
    tasklist = taskfile()
    userDictionary = userDict()
    userstats = userDictionary
    UserNumtasks = {}

    print("\nMenu option to generate user overview.")
    input("\nPress any key to continue: ")

        
    with open("user_overview.txt", "w") as f:
        totalUsers = len(userlist)
        totalTasks = len(tasklist)
        totalUsersstr = "Total number of users registered in system: {}".format(totalUsers)
        totalTasksstr = "Total number of tasks assigned to users:    {}".format(totalTasks)
        f.write(totalUsersstr + "\n")
        f.write(totalTasksstr + "\n")

        #Loooping through dictionaries to generate relavant data based on user name.
        for username in userDictionary.keys():
            UserNumtasks[username] =  0

        for name in UserNumtasks.keys():
            for usertask in tasklist:
                if name in usertask:
                    UserNumtasks[name] +=  1

        #Looping through two dictionaries to format data.
        for i, z in zip(UserNumtasks.keys(), UserNumtasks.values()):
            percentageUser = round( (z/(totalTasks) *100) , 2 )
            
            writestr = '''Total number of tasks assigned to {} is {}.
User's tasks as a percentage of total tasks:   {}%'''.format(i,z,percentageUser)
            userstats[i] = '''
Total number of tasks assigned to {} is {}.
User's tasks as a percentage of total tasks:   {}%'''.format(i,z,percentageUser)
        #Writing to the text file
            f.write(writestr + "\n")
        
        print("Successfuly generated the user overview report.\nPlease access it from the admin stats option.")
        menu_option()
        return userstats


def startMessage():
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
    


        #Function code for user log in with verification
def logIn():
    global myDict
    myDict = {}

    for line in userfile(): # Read the lines and store them in a dict{}
        
        login_info = line.strip("\n").split(", ")
        myDict[login_info[0]] = login_info[1]

    global username
    global password
    is_access = False
    while not is_access:

        username = input("\nPlease input your username: \n")
        username = username.lower()
        password = input("\nPlease input your password: \n")

            #Check if password submitted is linked to the username submitted.
        if (username in myDict): 
            if myDict[username] == password:
                #print(myDict[username])
                print("\nAccess Granted, Welcome!\n")
                is_access = True

            #print("yes to {}".format(username) )
            else:
                input("\nYour password is incorect. Please press any key to try again.")
        else:
            input("\nUsername is incorect. Please press any key to try again.")

    return username


#Function to display the main menu
def main_menu(username):

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
gr  -       generate reports
ds   -       View Statistics
e   -       exit
    '''


    global menu_choice 
    if username == "admin":
        print(admin_mainMenu)
        menu_choice = (input("Input here: ")).lower()
    else:
        print(main_menu)
        menu_choice = (input("Input here: ")).lower()
    return menu_choice



def user_choice():


    while True:

        if menu_choice == "r":
            reg_user(username, password)
            #print("User reg")
            break


        elif menu_choice == "a":
            add_task()
            #print("ADD Task")
            break


        elif menu_choice == "va":
            view_all()
            #print("View all tasks")
            break


        elif menu_choice == "vm":
            view_mine()
            #print("View my task")
            break

        elif menu_choice == "ds" and username == "admin":
            admin_stats()
            #print("Admin chose stats")
            break

        elif menu_choice == "gr" and username == "admin":
            print("Generate Reports under construction.")
            break

        elif menu_choice == "e":
            print('Exit goodbye')
            exit()

        else:
            input('\nInvalid choice, try again \n')
            main_menu(username)


            #Function code to enable user to return to menu after user is done with chosen option.
def menu_option():
    input("Press any key to continue")
    main_menu(username)
    user_choice()



                ########Functions for user choices########


        #Function code to register a new user

def reg_user(username, password):
    
    if username == "admin" and password == "adm1n":
        print('''
Menu Prompt to Register a New User
You are Rquired to provide:         New Username
                                    New Password
                                    Confirm Password
            
    ''')
        input("Press any key to continue \n")
                #Variables to register a new user
                
        #print(login_info)
        global new_user
        global new_pass
        isLoop = False
        pass_validation = False
        while not isLoop:
            new_user = input("Input new username: \n")
            if new_user not in myDict:
                new_pass = input("\nInput new password: \n")
                pass_confirm = input("\nReEnter password to confirm: \n")
                isLoop = True
                

            else:
                input("User is already registered in the system.\nEnter a key to try another user")
            

            #Iteration for vaidation
        while not pass_validation:
            if pass_confirm == new_pass:
                print("New password authenticated \n")
                print("\nYou have successfully registered a new user")
                pass_validation = True
            else:
                print("\nPassword is invalid. Please input correct password \n")
                pass_confirm = input("\nReInput Password please: \n")
            
                    #Appending to file
        regUser_file(new_user, new_pass)
        
        #Else statement if the user logged on is not 'admin'
    
    else:
        print("\nUser is not authorised to register a user. Try another option \n")
        menu_option()       #modify


        #Function to add a task to a user
def add_task():
    
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
                        #Writing to file
    string_file = new_usernme + ", " + title + ", " + description + ", " + date_given + ", " + due_date + ", " + is_task
    addTask_file(string_file)
    print("Successfully added task to file")
    menu_option()

        #Functions to view all tasks assigned to user

def view_all():
    
    print('''
Menu Prompt to view All Tasks

''')

    input("Press any key to continue: \n")

        #Displaying all tasks
    print("\nNow displaying all current tasks assigned: \n")
    num = 0

    for i in taskfile():

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
    menu_option()

        #Function code to edit a task

def editfile(taskComplete, taskNum):
    taskComplete = " " + taskComplete.capitalize()
    taskList = taskfile()
    for x in taskList:
        print(x)
    print("\n")
    userTask = taskList[taskNum].strip().split(",")
    
    new_state = taskList[taskNum].strip().replace(userTask[5], taskComplete)
    
    
    taskList[taskNum] = new_state
    for y in taskList:
        print(y)
    #with open('tasks.txt', 'w') as f:
     #   f.write(updated_string)

        #Function to view tasks specific to user

def view_mine():

    print('''
Menu Prompt to show user's current tasks: 
''')

    input("\nPress any key to continue: \n")

    noTask = False
    
    isTask = False
    num = 0
    tasklist = taskfile()
    for i in tasklist:
        
        if username in i:
            isTask = True
            current_task = i.strip().split(", ")
            print((current_task))
            tsk_user = current_task[0]
            tsk_description = current_task[2]
            tsk_title = current_task[1]
            tsk_date = current_task[3]
            tsk_dueDate = current_task[4]
            tsk_complete = current_task[5]
            num += 1

            task_display = '''
User's Task Number  :   {}

User:                   {}
Title:                  {}
Description:            {}
Date Given:             {}
Date Due:               {}
Task Complete?          {}
'''.format((num), tsk_user, tsk_title, tsk_description, tsk_date, tsk_dueDate, tsk_complete)

        
            print(task_display)
        else:
            num += 1
            noTask = True
    
    if noTask and not isTask:
        print("\nNo Current tasks for user.\n")

    
    isChoice = False
    while not isChoice:
        editTask = input("Would you like to edit a task? (Edit) or return to the menu? (-1)\n")
        editTask = editTask.lower()
        if editTask == "edit":
            global taskNum
            taskNum = int(input("Please enter the Task number?\n"))
            taskNum = taskNum - 1
            isChoice = True
            taskloop = False
            while not taskloop:
                taskComplete = input("Has this task been completed?\n")
                taskComplete = taskComplete.lower()
                if taskComplete == "yes":
                    editfile(taskComplete, taskNum)
                    print("\nSuccessfully changed the status of the selected task.")
                    taskloop = True
                    
                elif taskComplete == "no":
                    editfile(taskComplete, taskNum)
                    print("Successfully ammended the status of the selected task.")
                    taskloop = True
                    
                else:
                    input("Incorrect entry, press any key to enter again.")
                    
            menu_option()
        elif editTask == "-1":
            isChoice = True
            pass
        else:
                input("Incorrect entry, press any key to enter again.")
    menu_option()
    
#Function for the admin Statistics 

def admin_stats():
    

    print("\nMenu Prompts to view Program's Statistics \n")

    input("\nPress any key to continue...")
    user_choice = False
    print("\nWelcome to the admin statistics. Please select the option you would like to view")
    while not user_choice:
        
        choice = input("\nPress 1 for User Statistics: \nPress 2 for Task Statistics: \nPress -1 to go back to the main menu \n")

        if choice == "1":
        
            user_choice = True
        elif choice == "2":
            
            user_choice = True
        elif choice == "-1":
            user_choice = True
        else:
            print("\nInvalid choice, try again")
            
    if choice == "1":
        user_stats()
        menu_option()
    elif choice == "2":
        task_stats()
        menu_option()
    else:
        menu_option()
            
        
#Calling Functions for the main program

startMessage()
logIn()
main_menu(username)
user_choice()







