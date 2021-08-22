def userfile():
    user_file = open("user.txt","r")
    user_list = user_file.readlines()
    user_file.close
    print(user_list)
    return user_list

        #Function code to load data to task file and to wite it to a list
def taskfile():
    task_file = open("tasks.txt","r")#, encoding='utf-8')
    task_list = task_file.readlines()
    print(task_list)
    task_file.close
    return task_list


taskfile()
