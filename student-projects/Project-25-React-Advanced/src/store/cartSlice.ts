/**
 * ============================================================================
 * FILE: cartSlice.ts
 * TOPIC: Redux Toolkit — Creating a "slice" of state
 * ============================================================================
 *
 * A SLICE contains:
 *   1. State shape (interface)
 *   2. Initial state
 *   3. Reducers (functions that update state)
 *   4. Actions (auto-generated from reducer names)
 *
 * Redux Toolkit uses Immer internally, so you can write "mutating" code
 * in reducers and it creates immutable updates under the hood!
 *
 * INTERVIEW TIP:
 * "I use Redux Toolkit's createSlice which combines action creators and
 *  reducers in one place. It uses Immer for immutable updates, so I can
 *  write simpler reducer logic without manual spreading."
 * ============================================================================
 */

// import { createSlice, PayloadAction } from '@reduxjs/toolkit';

// TODO 1: Define the CartItem interface
// interface CartItem {
//     id: string;
//     name: string;
//     price: number;
//     quantity: number;
//     imageUrl?: string;
// }

// TODO 2: Define the CartState interface
// interface CartState {
//     items: CartItem[];
//     totalQuantity: number;
//     totalPrice: number;
// }

// TODO 3: Define initial state
// const initialState: CartState = {
//     items: [],
//     totalQuantity: 0,
//     totalPrice: 0,
// };

// TODO 4: Create the slice
// export const cartSlice = createSlice({
//     name: 'cart',
//     initialState,
//     reducers: {
//         // TODO 5: addToCart — add item or increase quantity if exists
//         addToCart(state, action: PayloadAction<Omit<CartItem, 'quantity'>>) {
//             const existing = state.items.find(item => item.id === action.payload.id);
//             if (existing) {
//                 existing.quantity += 1;  // Looks like mutation, but Immer handles it!
//             } else {
//                 state.items.push({ ...action.payload, quantity: 1 });
//             }
//             state.totalQuantity += 1;
//             state.totalPrice += action.payload.price;
//         },
//
//         // TODO 6: removeFromCart — decrease quantity or remove
//         removeFromCart(state, action: PayloadAction<string>) {
//             const index = state.items.findIndex(item => item.id === action.payload);
//             if (index >= 0) {
//                 const item = state.items[index];
//                 state.totalQuantity -= 1;
//                 state.totalPrice -= item.price;
//                 if (item.quantity === 1) {
//                     state.items.splice(index, 1);
//                 } else {
//                     item.quantity -= 1;
//                 }
//             }
//         },
//
//         // TODO 7: clearCart
//         clearCart(state) {
//             state.items = [];
//             state.totalQuantity = 0;
//             state.totalPrice = 0;
//         },
//     },
// });

// TODO 8: Export actions and reducer
// export const { addToCart, removeFromCart, clearCart } = cartSlice.actions;
// export default cartSlice.reducer;

