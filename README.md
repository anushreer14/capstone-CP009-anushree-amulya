# GoalVault: Goal-Based Savings and SIP Planning Engine

**Problem ID:** CP009

## Team Members

1. **Name:** Amulya S | **USN:** 1AT24CS018
2. **Name:** Anushree R | **USN:** 1AT24CS022

## Project Description

GoalVault is a menu-driven Java application designed to help users plan and manage their financial goals. It allows users to create savings goals, calculate the required monthly savings or SIP amount, record monthly contributions, and monitor their financial progress.

The application helps users understand how much they need to save regularly to achieve their financial goals within a specified time period.

## Technologies Used

- **Programming Language:** Java
- **IDE:** IntelliJ IDEA
- **Concepts:** Object-Oriented Programming (OOP)
- **Libraries:** Java Standard Library

## How to Run the Program

### Prerequisites

- Java Development Kit (JDK 17).
- IntelliJ IDEA

### Steps to Run

1. Clone or download this repository.
2. Open the project folder in IntelliJ IDEA.
3. Locate the `src` folder.
4. Open `Main.java`.
5. Run the `Main` class.
6. The menu-driven application will appear in the Run console.
7. Enter your choice to access the required functionality.

### Run Using Terminal

Navigate to the project directory and execute the following commands.

**Compile the Java files:**

```bash
javac -d out src/*.java
```

**Run the application:**

```bash
java -cp out Main
```

## Features Implemented

1. **Create New Goal:** Allows users to create financial goals by entering the goal name, target amount, initial savings, and target date.

2. **Calculate Monthly Savings / SIP:** Calculates the required monthly savings or estimated SIP investment based on the target amount, current savings, time period, and expected annual return.

3. **Record Monthly Contribution:** Allows users to record contributions towards their financial goals and validates contribution amounts.

4. **View Goal Progress:** Displays total savings, percentage of completion, and the remaining amount required to achieve the goal.

5. **Check Goal Status / Delay Warning:** Checks whether users are on schedule, behind schedule, or have completed their financial goals.

6. **View All Goals:** Displays all the financial goals created by the user along with their savings details.

7. **Exit:** Safely terminates the application.

## Project Structure

```text
GoalVault/
│
├── src/
│   ├── Main.java
│   ├── Goal.java
│   ├── Contribution.java
│   ├── GoalCalculator.java
│   └── GoalManager.java
│
├── README.md
└── .gitignore
```

## Sample Run

**Main Menu:**

```text
GOALVAULT
Goal-Based Savings and SIP Planning Engine

MAIN MENU

1. Create New Goal
2. Calculate Monthly Savings / SIP
3. Record Monthly Contribution
4. View Goal Progress
5. Check Goal Status / Delay Warning
6. View All Goals
7. Exit

Enter your choice:
```

**Sample Input:**

```text
Enter your choice: 7
```

**Sample Output:**

```text
Exiting GoalVault. Thank you!
```

*Note: The sample output illustrates the exit operation. Ensure that the displayed message matches your actual program output.*

## Conclusion

GoalVault provides a simple and systematic way to plan financial goals and track savings progress. By using Java and Object-Oriented Programming concepts, the application helps users calculate savings requirements, record contributions, and monitor their progress towards achieving their financial goals.