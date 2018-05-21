package com.udacity.gradle.builditbigger;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;


/**
 * Created by Mustafa Berkay Mutlu on 21.05.18.
 */
public class JokerAsyncTaskTest {

    private JokerAsyncTask jokerAsyncTask;

    @Mock
    private JokerAsyncTask.Callback callback;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        jokerAsyncTask = new JokerAsyncTask(callback);
    }

    @Test
    public void fetchesJokesCorrectly() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                latch.countDown();
                return null;
            }
        }).when(callback).onResult(anyString());

        jokerAsyncTask.execute();

        latch.await(5, TimeUnit.SECONDS);

        verify(callback).onResult(Mockito.anyString());
    }
}