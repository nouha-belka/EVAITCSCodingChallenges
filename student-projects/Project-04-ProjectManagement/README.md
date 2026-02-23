# Project 04: Project Management Tools - "ManageMyProject"

## 🎯 Objective
In the real world, developers don't just code — they also participate in
Agile ceremonies, manage tasks in Jira/Trello, and work in sprints.
In this project, you'll simulate running a 2-week sprint for building
a **To-Do List Application**.

## 📚 Topics Covered (from Study Material)
- Jira Setup & Configuration
- Workflow Stages (Todo → In Progress → Review → Done)
- Issue Types (Epic, Story, Task, Bug)
- Scrum Framework (Sprint Planning, Standups, Reviews, Retros)
- Kanban Methodology
- Agile Best Practices

---

## 📝 Tasks to Complete

### Task 1: Create a Product Backlog (product-backlog.md)
Create `product-backlog.md` with user stories for a To-Do List app:

Write at least 10 user stories following this format:
```
As a [type of user], I want to [action] so that [benefit].

Acceptance Criteria:
- [ ] Criterion 1
- [ ] Criterion 2
- [ ] Criterion 3

Story Points: ___
Priority: High / Medium / Low
```

Example:
```
US-001: As a user, I want to create a new task so that I can track things I need to do.

Acceptance Criteria:
- [ ] User can enter a task title (required, max 100 characters)
- [ ] User can add an optional description
- [ ] User can set a due date
- [ ] Task is saved and appears in the task list
- [ ] System shows a success message after task creation

Story Points: 3
Priority: High
```

### Task 2: Sprint Planning (sprint-plan.md)
Create `sprint-plan.md`:

1. **Sprint Goal:** Write a clear goal for a 2-week sprint.
2. **Sprint Backlog:** Select user stories from your backlog (aim for ~20 story points).
3. **Task Breakdown:** Break each story into technical tasks.

Example:
```
Sprint 1: "Core Task Management"
Sprint Goal: Users can create, view, edit, and delete tasks.
Capacity: 20 story points

Selected Stories:
| Story ID | Title              | Points | Tasks                                    |
|----------|-------------------|--------|------------------------------------------|
| US-001   | Create new task    | 3      | - Design UI form                         |
|          |                   |        | - Create Task model class                |
|          |                   |        | - Implement save functionality           |
|          |                   |        | - Write unit tests                       |
|          |                   |        | - Code review                            |
```

### Task 3: Daily Standup Log (standup-log.md)
Create `standup-log.md` — Simulate 5 days of standups:

```
## Day 1 - Monday
**What I did yesterday:** Sprint planning, set up project structure
**What I'm doing today:** Working on US-001 (Create new task) - designing the UI
**Blockers:** None

## Day 2 - Tuesday
**What I did yesterday:** Completed UI design for task creation form
**What I'm doing today:** Implementing Task model class and save logic
**Blockers:** Need clarification on date format requirements

(Continue for 5 days...)
```

### Task 4: Sprint Review & Retrospective (sprint-review.md)
Create `sprint-review.md`:

**Sprint Review:**
1. What was completed? (List completed stories with demo notes)
2. What was NOT completed? Why?
3. Stakeholder feedback (simulate 2-3 pieces of feedback)

**Sprint Retrospective:**
| What Went Well | What Could Improve | Action Items |
|---------------|-------------------|--------------|
| (At least 3)  | (At least 3)      | (At least 3) |

### Task 5: Kanban Board Simulation (kanban-board.md)
Create `kanban-board.md` — Show the state of your board at end of sprint:

```
| TODO          | IN PROGRESS    | REVIEW         | DONE              |
|---------------|---------------|----------------|-------------------|
| US-008        | US-006        | US-005         | US-001            |
| US-009        |               |                | US-002            |
| US-010        |               |                | US-003            |
|               |               |                | US-004            |
```

Set WIP limits and explain why you chose them.

---

## ✅ Submission Checklist
- [ ] `product-backlog.md` — At least 10 user stories with acceptance criteria
- [ ] `sprint-plan.md` — Sprint goal, selected stories, and task breakdown
- [ ] `standup-log.md` — 5 days of simulated standups
- [ ] `sprint-review.md` — Review and retrospective documentation
- [ ] `kanban-board.md` — Board state with WIP limits explained

## 💡 Interview Tip
Almost every tech company uses Agile. Interview questions like:
- "Describe your team's sprint process"
- "What's the difference between Scrum and Kanban?"
- "How do you estimate story points?"
These questions come up constantly — this project gives you real experience to discuss!

## 🏆 Bonus Challenge
Set up a FREE Trello or Jira board and actually move cards through the workflow.
Take screenshots and include them in a `screenshots/` folder.

