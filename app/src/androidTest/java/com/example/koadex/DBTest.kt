package com.example.koadex
/*
import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.koadex.data.FormDatabase
import com.example.koadex.data.FormDao
import com.example.koadex.data.FormEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DBTest {
    private lateinit var formDao: FormDao
    private lateinit var db: FormDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        // Use an in-memory database for testing
        db = Room.inMemoryDatabaseBuilder(
            context,
            FormDatabase::class.java
        ).build()
        formDao = db.formDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndRetrieveForm() = runBlocking {
        // Create a test form
        val testForm = FormEntity(
            name = "Test Event",
            date = "2024-03-13",
            place = "Test Location",
            hour = "14:00",
            weather = "Sunny",
            season = "Spring"
        )

        // Insert the form
        formDao.insert(testForm)

        // Get all forms and verify
        val allForms = formDao.getAllForms().first()
        assertEquals(1, allForms.size)
        assertEquals("Test Event", allForms[0].name)
    }

    @Test
    @Throws(Exception::class)
    fun deleteForm() = runBlocking {
        // Create and insert a test form
        val testForm = FormEntity(
            name = "Event to Delete",
            date = "2024-03-13",
            place = "Delete Location",
            hour = "15:00",
            weather = "Cloudy",
            season = "Spring"
        )

        formDao.insert(testForm)

        // Verify form was inserted
        var allForms = formDao.getAllForms().first()
        assertEquals(1, allForms.size)

        // Delete the form
        formDao.delete(allForms[0])

        // Verify form was deleted
        allForms = formDao.getAllForms().first()
        assertTrue(allForms.isEmpty())
    }

    @Test
    @Throws(Exception::class)
    fun insertMultipleFormsAndDeleteOne() = runBlocking {
        // Create multiple test forms
        val form1 = FormEntity(
            name = "Event 1",
            date = "2024-03-13",
            place = "Location 1",
            hour = "10:00",
            weather = "Sunny",
            season = "Spring"
        )

        val form2 = FormEntity(
            name = "Event 2",
            date = "2024-03-14",
            place = "Location 2",
            hour = "11:00",
            weather = "Rainy",
            season = "Spring"
        )

        // Insert both forms
        formDao.insert(form1)
        formDao.insert(form2)

        // Verify both forms were inserted
        var allForms = formDao.getAllForms().first()
        assertEquals(2, allForms.size)

        // Delete the first form
        formDao.delete(allForms[0])

        // Verify only one form remains and it's the correct one
        allForms = formDao.getAllForms().first()
        assertEquals(1, allForms.size)
        assertEquals("Event 2", allForms[0].name)
    }
}*/