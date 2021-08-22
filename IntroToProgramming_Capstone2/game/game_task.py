import pygame
import random

# initialize pygame modules
pygame.init()

# create screen
# The screen that will be created needs a width and a height.
screen_width = 1040
screen_height = 680

# This creates the screen and gives it the width and height specified as a 2 item sequence.
# This creates the screen and gives it the width  and height specified as a 2 item sequence.
screen = pygame.display.set_mode((screen_width, screen_height))

#Naming of the title window
icon = pygame.display.set_caption("Comicadres of The force Europa!!!")

#Definaming the player and the image to be used
player = pygame.image.load('Task 15\game\dude.png')

#Defining the background image to be used
grass = pygame.image.load("Task 15\game\cbackground_image.png")
grass = pygame.transform.scale(grass, (1040, 680))

#Using the get() to create and store values of the background image and of the player image
back_height = grass.get_height()
back_width = grass.get_width()

image_height = player.get_height()
image_width = player.get_width()


                #Defining the starting point of the player using X and Y variables
playerXPosition = 10
playerYPosition = 10



                            #Defining Enemy elements:
                #Storing all enemy elements in a list because there will be more than one enemy

#Lists defining the enemy, the enemy's X and Y positions and their increment values:
enemy = []
enemyXPosition = []
enemyYPosition = []
enemyPosY = []
enemyPosX = []
enemy_height = []
enemy_width = []



                            #Defining the Prize elements:
                ##Storing all prize elements in a list because there will be more than one prize
#Lists defining the enemy, the enemy's X and Y positions and their increment values:
prize = []
prizeXPosition = []
prizeYPosition = []
prizePosY = []
prizePosX = []
prize_height = []
prize_width = []



                            #Defining a variable count

                #This is used as a control variable to exit the loop and end the game
                #Variable n is set to 3 to create the number of enemies and prize elements in the game.
count = 0
n = 8



                            #Defining the text to be displayed in game:
            #Defining text of score
            #Defining X and Y positions of the text will be placed

font = pygame.font.Font("freesansbold.ttf", 42)
textX = 10
textY = 10
target_text_x = 600
target_text_y = 10

            #Defining text and font of the 'Lose' message
over_font = pygame.font.Font("freesansbold.ttf", 75)
game_0ver_text_x = 250
game_0ver_text_y=200


            #Defining text and font of the 'Win' message
win_font = pygame.font.Font("freesansbold.ttf", 105)
win_text_x=250
win_text_y=200




                            #Defining the enemy elements:
            #For this, I will be using a for loop to iterate 3 diffrent definings of the enemies and the prizes
            #This will be done by leading the enemy image, defining their X and Y starting and increment values
for i in range(n):
    # load image of zombie
    enemy.append(pygame.image.load("Task 15\game\castle.png"))
    # set x position of each enemy
    enemyXPosition.append(random.randint(100, 680))
    # set y  position of each enemy
    enemyYPosition.append(random.randint(40, 1020))
    # set the amount of distance each enemy will move with in the y direction
    enemyPosY.append(40)
    # set the amount of distance each enemy will move with in the x direction
    enemyPosX.append(0.265)
    # get the height of each the enemy image
    enemy_height.append(enemy[i].get_height())
    # get the witdh of each the enemy image
    enemy_width.append(enemy[i].get_width())

            #Initialisation of the prize elements:
    prize.append(pygame.image.load("Task 15\game\cbadguy3.png"))
    # set x position of each prize element
    prizeXPosition.append(random.randint(10, 680))
    # set y position of each p
    prizeYPosition.append(random.randint(0, 1020))
    # set the amount of distance each enemy will move with in the y direction
    prizePosY.append(60)
    # set the amount of distance each enemy will move with in the x direction
    prizePosX.append(0.365)
    # get the height of each the prize image
    prize_height.append(prize[i].get_height())
    # get the witdh of each the prize image
    prize_width.append(prize[i].get_width())


                                    #Defining of Keyboard functions:
                #We initially set the keys to false until pressed where they become true.
# set the Key up on keyborad to false or not pressed
KeyUP = False
# set the Key down on keyborad to false or not pressed
KeyDown = False
# set the Key left on keyborad to false or not pressed
KeyLeft = False
# set the Key right on keyborad to false or not pressed
KeyRight = False




                                #-----CODE BELOW REPRESENTS THE GAME ITSELF-------#


                                #Defing of boolean values 'Lost' and 'Won'
lost = False
won = False



                                #Using a while loop to run the game until counter variable is incremented
while 1:

    #Setting a target for the user to score to win.
    target = 2500
    

    #Clears the screen.
    screen.fill(0)

    #Drawing background image onto the screen
    #for x in range(screen_width//back_width +1):
       #for y in range(screen_height//back_height +1):
    #screen.blit(grass,((*100,y*100)))
    

            #This draws the player image to the screen at the postion specfied. I.e. (100, 50).
    screen.blit(player, (playerXPosition, playerYPosition))

           #This renders the score of the player to the screen at the postion specfied and with the color white
    score = font.render("Score: " + str(count), True, (255, 235, 184))
    game_instruction = font.render("Hit animal and live! Hit castle and die!", True, (255,235,184))
    screen.blit(score, (textX, textY))
    screen.blit(game_instruction,(textX -100, textY - 20))

            #This renders the target for the player to the screen at the postion specfied and with the color white
    target = font.render("Score To Win: " + str(target), True, (192, 255, 220))
    screen.blit(target, (target_text_x, target_text_y))



                #Using a for loop to draw the enemy and prize to the screen 3 times.
    for i in range(n):
        screen.blit(enemy[i], (enemyXPosition[i], enemyYPosition[i]))
        screen.blit(prize[i], (prizeXPosition[i], prizeYPosition[i]))

                #This updates the screen.
    pygame.display.flip()  

                #for loop to always check the events of the game
    for event in pygame.event.get():
                #Check to see if user has requested to exit game
        if event.type == pygame.QUIT:
            pygame.quit()

                #Check to see if a key has been pressed by the user
        if event.type == pygame.KEYDOWN:
                #Check to see if up key is pressed
            if event.key == pygame.K_UP:
                KeyUP = True
                #Check to see if down key is pressed
            if event.key == pygame.K_DOWN:
                KeyDown = True
                #Check to see if left key is pressed
            if event.key == pygame.K_LEFT:
                KeyLeft = True
                #Check to see if right key was pressed
            if event.key == pygame.K_RIGHT:
                KeyRight = True
                

                            #A check to see if a key has been released to update the status.
        if event.type == pygame.KEYUP:
            #Check to see if up key is released
            if event.key == pygame.K_UP:
                KeyUP = False
            #Check to see if down key is released
            if event.key == pygame.K_DOWN:
                KeyDown = False
            #Check to see if left key is released
            if event.key == pygame.K_LEFT:
                KeyLeft = False
            #Check to see if right key was released
            if event.key == pygame.K_RIGHT:
                KeyRight = False


                            #Moving the player element accpording to which key has been pressed.
        
        if KeyUP:
            #This makes sure that the user does not move the player above the window.
            if playerYPosition > 10:
                                                            #Will move the player by up by 9 from its position
                playerYPosition -= 50
        
        if KeyDown:
            # This makes sure that the user does not move the player above the below.
            if playerYPosition < (screen_height - 80) - image_height:
                                                            #Will move the player by down by 9pixels from its position
                playerYPosition += 50

        #Move the player to the left
        if KeyLeft:
            if playerXPosition > 10:
                playerXPosition -= 50

        #Move player to the right
        if KeyRight:
            if playerXPosition < 1000:
                # move the player to the right by 9 pixels from its position
                playerXPosition += 50
    # for loop for each enemy and prize to keep moving
    for i in range(n):
        # add pixels to the enemy[i] x position
        enemyXPosition[i] = enemyXPosition[i] + enemyPosX[i]
        # add pixels to the prize [i] position
        prizeXPosition[i] = prizeXPosition[i] + prizePosX[i]

        #If the enemy is at the egde of the left side you can move it to the right
        if enemyXPosition[i] <= 0:
            enemyPosX[i] = 0.236
                                    #If the enemy is at the right, you can move it to the left
            if enemyYPosition[i] < 600:
                enemyYPosition[i] += enemyPosY[i]
            else:
                enemyYPosition[i] = 0

        #If the enemy is at the egde of the right side you can move it to the left
        elif enemyXPosition[i] >= 1000:
                                    #Move player to the left
            enemyPosX[i] = -0.236
            # if enemy is not below 600 pixels in the y direction the it should still move up
            if enemyYPosition[i] < 600:
                # move enemy down
                enemyYPosition[i] += enemyPosY[i]
            else:
                # make enemy y position zero
                enemyYPosition[i] = 0
        # if prize is at the left
        if prizeXPosition[i] <= 0:
            # move prize to the right
            prizePosX[i] = 0.236
            # if the prize is below 600 the prize can move down
            if prizeYPosition[i] < 600:
                # the prize should move down
                prizeYPosition[i] += prizePosY[i]
            else:
                # set the y position of the prize to zero
                prizeYPosition[i] = 0
        # if the prize is at or below 1000 the prize should move up
        elif prizeXPosition[i] >= 1000:
            # move the prize up
            prizePosX[i] = -0.236
            # if the prize is below 600
            if prizeYPosition[i] < 600:
                # the prize should move up
                prizeYPosition[i] += prizePosY[i]
            else:
                # set y position of the prize to zero
                prizeYPosition[i] = 0







                                            #Bounding box for the player:

        #Define the player image in a rectangle
        playerBox = pygame.Rect(player.get_rect())
        playerBox.top = playerYPosition
        playerBox.left = playerXPosition

                                            #Bounding box for the enemy:

        #Define the enemy image in a rectangle
        enemyBox = pygame.Rect(enemy[i].get_rect())
        enemyBox.top = enemyYPosition[i]
        enemyBox.left = enemyXPosition[i]

                                            #Bounding box for the prize:
        #Define the prize image in a rectangle
        prizeBox = pygame.Rect(prize[i].get_rect())
        prizeBox.top = prizeYPosition[i]
        prizeBox.left = prizeXPosition[i]

        

                                    #If the player box collides with the prize the game should quit
        if playerBox.colliderect(prizeBox):
            count += 1
        

                                    #If the player collects  more than 2500 coins then they win
        if count >= 2500:
            won = True


                                    #If the player collides with the enemy then the game quits
        if playerBox.colliderect(enemyBox):
            lost = True



                                    #If statements to quit game:
    if lost:
        over_text = over_font.render("GAME OVER ", True, (255, 255, 255))
        screen.blit(over_text, (200, 250))
        pygame.display.flip()                   #This updates the screen.
        quit()


    if won:
         win_text = over_font.render("YOU WIN!!!!!", True, (255, 255, 255))
         screen.blit(win_text, (200, 250))
         pygame.display.flip()  
         quit()






#----------------------------------REFERENCES--------------------------------------------------#




#       1. HyperionDev Task 15 example.py
#       2. Beginning Game Programming for Teens with Python - Julian Meyer
#           https://www.raywenderlich.com/2795-beginning-game-programming-for-teens-with-python
#       3. How to Make A Simple Game in Python (For Beginners)
#           https://www.youtube.com/watch?v=BDi3SD7E6no&ab_channel=TechWithTim
#       4. Pygame fundamentals from pygame.org
#           https://www.pygame.org/docs/tut/tom_games2.html#makegames-2


#-----------------------------NOTES-------------------------------------------------------------#

            #In this project I created a game called Comicadres of The force Europa with 
            #   1 player object
            #   8 enemy objects(castles)
            #   8 small prizes(mice)

            #The player object can move with 4 arrows while the 6 different objects move from right to left.
            #A counter counts the score up until 2500 where the player will be declared the winner
            #If the player collides with the castle, the game will end. The player has lost.

        
    #Issues I came across that I may need assistance with:
        #I tried to place a backround image to the screen but while I was able to do so successfully,
        # the game objects started to move slower than usual.

        #I then tried to place a background color but this too was unseccessful as this resulted in an incorrect output.

            


