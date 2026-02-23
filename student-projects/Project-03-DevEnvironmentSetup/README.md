# Project 03: Development Environment Setup - "SetUpMyWorkspace"

## 🎯 Objective
Every developer needs to set up their development environment from scratch.
In this project, you will configure a complete Java development workspace,
learn essential Git commands, and practice the workflows used in real companies.

## 📚 Topics Covered (from Study Material)
- Git Fundamentals (repository, staging, commits)
- Essential Git Commands
- Git Best Practices & Branching Strategy
- IDE Configuration (IntelliJ IDEA)
- Package Managers & Build Tools
- Environment Configuration

---

## 📝 Tasks to Complete

### Task 1: Git Repository Setup (git-log.md)
Perform the following Git operations and document each step in `git-log.md`:

1. **Initialize a new Git repository:**
   ```bash
   # TODO: Write the command to initialize a new Git repository
   ```

2. **Create a .gitignore file** with appropriate entries for a Java project:
   ```
   # TODO: Add entries to ignore compiled files, IDE files, etc.
   # Hint: What files should NOT be tracked? (.class, .idea/, target/, etc.)
   ```

3. **Make your first commit:**
   ```bash
   # TODO: Stage all files and create an initial commit
   # Write a meaningful commit message!
   ```

4. **Create a feature branch:**
   ```bash
   # TODO: Create and switch to a branch called "feature/add-readme"
   ```

5. **Make changes on the feature branch, commit, and merge back to main:**
   ```bash
   # TODO: Add a README.md file on the feature branch
   # TODO: Commit your changes
   # TODO: Switch back to main
   # TODO: Merge the feature branch into main
   ```

6. **Document your Git log** — paste the output of `git log --oneline --graph`

### Task 2: Branching Exercise (branching-exercise.md)
Create `branching-exercise.md` and answer:

1. Explain the difference between `git merge` and `git rebase`.
2. What is a **pull request** and why is it important?
3. Describe a **Git branching strategy** your team could use (e.g., GitFlow, GitHub Flow).
4. Draw a branch diagram showing:
   - main branch
   - develop branch
   - Two feature branches
   - A hotfix branch

Example:
```
main:     o---o-----------o---o (merge hotfix)---o (release)
               \         /                       /
develop:        o---o---o-----------o-----------o
                 \       \         /
feature/login:    o---o   \       /
                           \     /
feature/signup:             o---o
```

### Task 3: IDE Configuration Guide (ide-setup.md)
Create `ide-setup.md` — a guide for a NEW developer joining your team:

1. **Which IDE** to use and why (IntelliJ IDEA recommended for Java).
2. **Essential plugins/extensions** to install (list at least 5).
3. **Code formatting rules** your team follows.
4. **Debugging setup** — explain how to set a breakpoint and run the debugger.
5. **Keyboard shortcuts** — list 10 essential shortcuts for productivity.

### Task 4: Environment Setup Checklist (env-checklist.md)
Create `env-checklist.md`:

```markdown
## New Developer Onboarding Checklist

### Required Software
- [ ] JDK (version: ___)  — Download from: ___
- [ ] IntelliJ IDEA       — Download from: ___
- [ ] Git                  — Download from: ___
- [ ] Node.js              — Download from: ___
- [ ] MySQL/PostgreSQL     — Download from: ___
- [ ] Postman              — Download from: ___

### Environment Variables to Set
- [ ] JAVA_HOME = ___
- [ ] PATH includes ___
- [ ] (Add any others needed)

### Verification Commands
- [ ] `java -version` outputs: ___
- [ ] `git --version` outputs: ___
- [ ] `node -v` outputs: ___
- [ ] `mvn -version` outputs: ___

### Project Setup Steps
1. Clone the repository: `git clone ___`
2. Import project into IDE
3. Build the project: ___
4. Run the tests: ___
5. Start the application: ___
```

Fill in ALL the blanks with real values from YOUR system!

---

## ✅ Submission Checklist
- [ ] `git-log.md` — All Git commands documented with outputs
- [ ] `.gitignore` — Proper Java project gitignore file created
- [ ] `branching-exercise.md` — Branching questions answered with diagram
- [ ] `ide-setup.md` — Complete IDE configuration guide
- [ ] `env-checklist.md` — Filled-in checklist with real system values

## 💡 Interview Tip
Interviewers LOVE asking about Git workflows. Common questions include:
- "How do you resolve a merge conflict?"
- "What branching strategy does your team use?"
- "Explain the difference between git pull and git fetch."
Practice these — they come up in almost every interview!

## 🏆 Bonus Challenge
Create a **merge conflict on purpose**, then resolve it and document the process
in `merge-conflict-resolution.md`. Show the conflict markers and your resolution.

