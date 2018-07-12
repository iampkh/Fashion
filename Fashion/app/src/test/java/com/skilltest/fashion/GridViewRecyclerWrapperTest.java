package com.skilltest.fashion;

import org.junit.Before;
import org.mockito.Mock;
import org.junit.Test;
import org.junit.Rule;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.MockitoJUnit;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.Assert.*;
import com.skilltest.fashion.ui.adapters.*;
import android.support.v7.widget.GridLayoutManager;

import android.support.v7.widget.RecyclerView;


public class GridViewRecyclerWrapperTest {

    @Mock RecyclerView mRecycleView;
    @Mock GridLayoutManager mLayoutManager;
    GridViewRecyclerWrapper mRecyclerWrapper;


    @org.junit.Rule public MockitoRule mockitoRule = MockitoJUnit.rule();


    @Before
    public void setup(){
      mRecyclerWrapper = new GridViewRecyclerWrapper(mRecycleView);
      mRecyclerWrapper.setLayoutManager(mLayoutManager);


    }

    @Test
    public void testGetCurrentPosition() {
        when(mRecycleView.getLayoutManager()).thenReturn(mLayoutManager);
        when(((GridLayoutManager) mRecycleView.getLayoutManager())
                    .findFirstVisibleItemPosition()).thenReturn(2);
        assertEquals(2,mRecyclerWrapper.getCurrentPosition());
        verify(mLayoutManager).findFirstVisibleItemPosition();
    }

    @org.junit.After
    public void tearDown() {
        mRecycleView = null;
        mLayoutManager = null;
        mRecyclerWrapper = null;
    }
}