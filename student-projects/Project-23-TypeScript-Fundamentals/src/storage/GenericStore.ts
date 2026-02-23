/**
 * ============================================================================
 * FILE: GenericStore.ts
 * TOPIC: Generics — Type-safe reusable data structures
 * ============================================================================
 *
 * GENERICS are like Java generics: List<T>, Map<K,V>
 * They let you write code that works with ANY type while keeping type safety.
 *
 * Think of <T> as a PLACEHOLDER that gets filled in when you use the class:
 *   new GenericStore<Task>()  → T becomes Task
 *   new GenericStore<User>()  → T becomes User
 *
 * INTERVIEW TIP:
 * "Generics allow me to write reusable, type-safe code. Instead of using
 *  'any' which loses type checking, generics preserve the type information
 *  throughout the code."
 * ============================================================================
 */

// TODO 1: Define a generic interface for items that have an ID
// export interface Identifiable {
//     id: string;
// }

// TODO 2: Create a generic GenericStore class
// The constraint <T extends Identifiable> means T MUST have an id field.
//
// export class GenericStore<T extends Identifiable> {
//     private items: Map<string, T> = new Map();
//
//     // TODO 3: add(item: T): void
//     // TODO 4: getById(id: string): T | undefined
//     // TODO 5: getAll(): T[]
//     // TODO 6: update(id: string, updates: Partial<T>): T | undefined
//     // TODO 7: delete(id: string): boolean
//     // TODO 8: find(predicate: (item: T) => boolean): T[]
//     // TODO 9: count(): number
// }

// TODO 10: Create a generic Result type for operation outcomes
// export type Result<T> =
//     | { success: true; data: T }
//     | { success: false; error: string };
//
// This is a DISCRIMINATED UNION — you can check success to narrow the type:
//   if (result.success) { result.data is available }
//   else { result.error is available }

