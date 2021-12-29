package io.nandha.wallpaperapp.core.utils

import com.google.gson.JsonSyntaxException
import io.nandha.wallpaperapp.data.model.Image

import org.junit.Test
import java.lang.IllegalArgumentException

class ExtensionUtilsKtTest {

    @Test(expected = IllegalArgumentException::class)
    fun `toImageList should throw IllegalArgumentException`() {
        "".toImageList()
    }

    @Test(expected = JsonSyntaxException::class)
    fun `toImageList should throw JsonSyntaxException`() {
        "{".toImageList()
    }

    @Test
    fun sortByDate() {
        val image1 = Image("", "2010-01-21", "", "", "", "")
        val image2 = Image("", "2011-01-01", "", "", "", "")
        val image3 = Image("", "2010-02-21", "", "", "", "")
        val image4 = Image("", "2010-06-05", "", "", "", "")
        val image5 = Image("", "2009-12-29", "", "", "", "")
        val image6 = Image("", "2008-01-21", "", "", "", "")
        val list = listOf(image1, image2, image3, image4, image5, image6).sortByDate()
        val sortedList = listOf(image2, image4, image3, image1, image5, image6)
        assert(list == sortedList)
    }
}