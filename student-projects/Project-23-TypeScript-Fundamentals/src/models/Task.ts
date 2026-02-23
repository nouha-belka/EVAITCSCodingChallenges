/**
 * ============================================================================
 * FILE: Task.ts
 * TOPIC: TypeScript Interfaces, Types, Enums, and Union Types
 * ============================================================================
 *
 * INTERFACES vs TYPE ALIASES:
 *   interface → describes the SHAPE of an object (extendable)
 *   type      → can describe any type (unions, intersections, primitives)
 *
 * RULE OF THUMB:
 *   Use interface for objects, use type for everything else.
 *
 * INTERVIEW TIP:
 * "Interfaces define object shapes and are extendable. Type aliases are
 *  more flexible — they support unions and intersections. I use interfaces
 *  for public API contracts and types for internal logic."
 * ============================================================================
 */

// TODO 1: Define a Priority enum
// export enum Priority {
//     LOW = "LOW",
//     MEDIUM = "MEDIUM",
//     HIGH = "HIGH",
//     URGENT = "URGENT"
// }

// TODO 2: Define a Status type using union types
// export type Status = "TODO" | "IN_PROGRESS" | "DONE" | "CANCELLED";

// TODO 3: Define the Task interface
// export interface Task {
//     id: string;
//     title: string;
//     description?: string;      // '?' means OPTIONAL
//     priority: Priority;
//     status: Status;
//     dueDate?: Date;
//     tags: string[];
//     createdAt: Date;
//     updatedAt: Date;
// }

// TODO 4: Define a CreateTaskInput type using Utility Types
// Omit<Task, 'id' | 'createdAt' | 'updatedAt'> removes fields that are auto-generated
// export type CreateTaskInput = Omit<Task, 'id' | 'createdAt' | 'updatedAt'>;

// TODO 5: Define an UpdateTaskInput type using Partial
// Partial<T> makes ALL fields optional — perfect for updates
// export type UpdateTaskInput = Partial<Omit<Task, 'id' | 'createdAt'>>;

// TODO 6: Define a TaskSummary using Pick
// Pick<Task, 'id' | 'title' | 'status' | 'priority'> selects only specific fields
// export type TaskSummary = Pick<Task, 'id' | 'title' | 'status' | 'priority'>;

// TODO 7: Define a TaskFilter interface for searching/filtering tasks
// export interface TaskFilter {
//     status?: Status;
//     priority?: Priority;
//     searchTerm?: string;
//     tags?: string[];
// }

