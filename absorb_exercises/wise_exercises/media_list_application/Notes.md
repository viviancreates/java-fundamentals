# Steps taken

1. Set up file structure and given class, fields, getters, setters
2. Make media and video class work
   - all extended classes added
   - test classes on main
3. media service 
   - add Media
   - remove media - boolean
     - loop through each return true or false
     - im sure there is a way to take out the loop from find and remove media 
4. main

# Notes to self
Initially, I wanted to set up the project based on all specs given so it would be easier to work on having a skeleton of the project
- This is making it overwhelming, also starting to try to work from memory
  - forgot setters 
  - parameters in constructors
- Next time, I would start with just the media abstract class, and one subclass like video
- COMMANDS
  - imagine a remote control with buttons
    - button 1 adds movie
    - button 2 removes movie
    - button 3 plays movie
  - ... the remote does not know how to add movie, remove mocie or play movie
    - it just sends a signal when a button is pressed
    - each command is a tiny robot with one job -> when you press the button, the remote says "hey media command, do action"
    



# Questions
- how to decide when setters are needed? added all to skeleton

# Reminders