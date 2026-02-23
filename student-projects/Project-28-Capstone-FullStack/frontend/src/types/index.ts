/**
 * ============================================================================
 * FILE: types/index.ts — Shared TypeScript types for the Job Board
 * ============================================================================
 */

// TODO 1: Define Job interface (matches backend Job entity)
// export interface Job {
//     id: number;
//     title: string;
//     description: string;
//     location: string;
//     salaryMin: number;
//     salaryMax?: number;
//     category: string;
//     employmentType: string;
//     experienceLevel: string;
//     isActive: boolean;
//     postedAt: string;
//     expiresAt?: string;
//     company: Company;
// }

// TODO 2: Define Company interface
// export interface Company {
//     id: number;
//     name: string;
//     industry: string;
//     website?: string;
//     logoUrl?: string;
//     description?: string;
//     location: string;
//     size: string;
// }

// TODO 3: Define Application interface
// export interface JobApplication {
//     id: number;
//     jobId: number;
//     jobTitle: string;
//     companyName: string;
//     status: ApplicationStatus;
//     coverLetter?: string;
//     appliedAt: string;
// }

// TODO 4: Define enums/union types
// export type ApplicationStatus = 'PENDING' | 'REVIEWED' | 'INTERVIEW' | 'OFFER' | 'REJECTED';
// export type EmploymentType = 'FULL_TIME' | 'PART_TIME' | 'CONTRACT' | 'REMOTE';
// export type ExperienceLevel = 'ENTRY' | 'MID' | 'SENIOR' | 'LEAD';

// TODO 5: Define auth types
// export interface User {
//     id: number;
//     username: string;
//     email: string;
//     firstName: string;
//     lastName: string;
//     role: 'ROLE_APPLICANT' | 'ROLE_RECRUITER' | 'ROLE_ADMIN';
// }

// export interface LoginRequest { username: string; password: string; }
// export interface LoginResponse { token: string; user: User; }
// export interface RegisterRequest { username: string; email: string; password: string; role: string; }

// TODO 6: Define filter/search types
// export interface JobFilter {
//     query?: string;
//     category?: string;
//     location?: string;
//     employmentType?: EmploymentType;
//     experienceLevel?: ExperienceLevel;
//     salaryMin?: number;
//     salaryMax?: number;
// }

