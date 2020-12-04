package com.example.lacochalitafinal.ui.cart;

import android.content.Context;

import com.example.lacochalitafinal.Common.Common;
import com.example.lacochalitafinal.Database.CartDataSource;
import com.example.lacochalitafinal.Database.CartDatabase;
import com.example.lacochalitafinal.Database.CartItem;
import com.example.lacochalitafinal.Database.LocalCartDataSource;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class CartViewModel extends ViewModel {

    private CompositeDisposable compositeDisposable;
    private MutableLiveData<List<CartItem>> mutableLiveDataCartItems;
    private CartDataSource cartDataSource;

    public CartViewModel() {
        compositeDisposable = new CompositeDisposable();
    }

    public void initCartDataSource(Context context) {
        cartDataSource = new LocalCartDataSource(CartDatabase.getInstance(context).cartDAO());
    }

    public void onStop() {
        compositeDisposable.clear();
    }

    private void getAllCartItems()
    {
        compositeDisposable.add(cartDataSource.getAllCart(Common.currentUser.getUid())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(cartItems -> {
                mutableLiveDataCartItems.setValue(cartItems);
        }, throwable ->{
            mutableLiveDataCartItems.setValue(null);

        })); //pt 16 22:se
    }

    public MutableLiveData<List<CartItem>> getMutableLiveDataCartItems() {
        if (mutableLiveDataCartItems == null)
        {
            mutableLiveDataCartItems = new MutableLiveData<>();
        }
        getAllCartItems();
        return mutableLiveDataCartItems;
    }
}