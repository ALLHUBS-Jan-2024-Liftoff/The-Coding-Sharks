# The-Coding-Sharks

**Wanderlust** is a travel itinerary app designed to help users log into their accounts, create trips, and manage destinations. Features include:
- Creating an account to save user profile.
- Adding and managing trips with custom destinations.
- Generating random destinations for inspiration.
- Searching for trips to find new travel ideas.
- Create a travel pack list to share with group.



# Installation and Setup

## FRONTEND (UI)
- Navigate to the 'The-Coding-Sharks-UI" directory
- Open the directory using VSCode
- Install dependencies using 'npm install'

## BACKEND (API)
- Navigate to the "The-Coding-Sharks-API" directory
- Open the directory using IntelliJ

## RELATIONAL DATABASE (MySQL)
- Start a MySQL server and create a new Schema called 'wanderlust'
- From the file applications.properties you will find the database connection settings:
    spring.datasource.url=jdbc:mysql://localhost:3306/wanderlust
    spring.datasource.username=wanderlust
    spring.datasource.password=wanderlust
- Use these connection settings to allow user access to MySQL

## RUNNING THE APPLICATION
- In VSCode, start the UI using the command 'npm run dev'
- In IntelliJ, run the API with 'BootRun' from the top-level file
# Once both frontend and backend are running:
- Open your browser and navigate to 'http://localhost:5173' to access Wanderlust UI
- Follow the on screen instructions to creating an account, logging in, creating a trip, and exploring other features. 


Strech goals to add or remove from ReadME later:
- Adding friends to trips for group coordination.