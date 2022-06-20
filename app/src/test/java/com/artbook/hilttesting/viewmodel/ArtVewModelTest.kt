package com.artbook.hilttesting.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.artbook.hilttesting.MainCoroutineRule
import com.artbook.hilttesting.getOrAwaitValueTest
import com.artbook.hilttesting.repository.FakeArtRepository
import com.artbook.hilttesting.util.Status
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ArtVewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var artViewModel: ArtViewModel

    @Before
    fun setUp() {
        //Test Doubles

        artViewModel = ArtViewModel(FakeArtRepository())
    }

    @Test
    fun `insert art without year returns error`() {
        artViewModel.makeArt("Mona Lisa", "Da Vinci", "")
        val value = artViewModel.insertArtMessage.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert art without name returns error`() {
        artViewModel.makeArt("","Da Vinci","1500")
        val value = artViewModel.insertArtMessage.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert art without artistName returns error`() {
        artViewModel.makeArt("Mona Lisa","","1500")
        val value = artViewModel.insertArtMessage.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.ERROR)
    }
}