package com.example.lacochalitafinal.Database;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class LocalCartDataSource implements CartDataSource {

    private CartDAO cartDAO;

    public LocalCartDataSource(CartDAO cartDAO) {
        this.cartDAO = cartDAO;
    }

    @Override
    public Flowable<List<CartItem>> getAllCart(String uid) {
        return cartDAO.getAllCart(uid);
    }

    @Override
    public Single<Integer> countItemInCart(String uid) {
        return cartDAO.countItemInCart(uid);
    }

    @Override
    public Single<Double> sumPriceInCart(String uid) {
        return cartDAO.sumPriceInCart(uid);
    }

    @Override
    public Single<CartItem> getItemsInCart(String foodId, String uid) {
        return cartDAO.getItemsInCart(foodId, uid);
    }

    @Override
    public Completable insertOrReplaceAll(CartItem... cartItems) {
        return cartDAO.insertOrReplaceAll(cartItems);
    }

    @Override
    public Single<Integer> updateCartItems(CartItem cartItems) {
        return cartDAO.updateCartItems(cartItems);
    }

    @Override
    public Single<Integer> deleteCartItem(CartItem cartItems) {
        return cartDAO.deleteCartItem(cartItems);
    }

    @Override
    public Single<Integer> cleanCart(String uid) {
        return cartDAO.cleanCart(uid);
    }

    @Override
    public Single<CartItem> getItemsWithAllOptionsInCart(String uid, String foodId) {
        return cartDAO.getItemsWithAllOptionsInCart(uid, foodId);

    }

    public CartDAO getCartDAO() {
        return cartDAO;
    }

    public void setCartDAO(CartDAO cartDAO) {
        this.cartDAO = cartDAO;
    }
}
