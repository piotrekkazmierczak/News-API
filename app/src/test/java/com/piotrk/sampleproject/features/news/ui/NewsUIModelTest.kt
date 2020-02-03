package com.piotrk.sampleproject.features.news.ui

import com.piotrk.sampleproject.data.NewsDateTime
import org.junit.Test

class NewsUIModelTest {

    private val noContent = "No content available for preview"
    private val noDescription = "No description provided"

    @Test
    fun test_modelFormatsDateSuppliedWithModel() {
        val newsUIModel = NewsUIModel("", null, null, NewsDateTime.of(2020, 1, 1,20,0), null, noContent, noDescription)
        assert(newsUIModel.getFormattedDate() == "Wed, Jan 1 - 8:00 PM")
    }

    @Test
    fun test_modelDisplaysNoContentMessageWhenContentFieldIsNull() {
        val newsUIModel = NewsUIModel("", null, null, NewsDateTime.of(2020, 1, 1,20,0), null, noContent, noDescription)
        assert(newsUIModel.getFormattedContent() == noContent)
    }

    @Test
    fun test_modelDisplaysContentMessageWhenContentFieldIsNotNull() {
        val newsUIModel = NewsUIModel("", null, null, NewsDateTime.of(2020, 1, 1,20,0), "This is content", noContent, noDescription)
        assert(newsUIModel.getFormattedContent() != noContent)
    }

    @Test
    fun test_modelDisplaysNoDescriptionTextWhenDescriptionFieldIsNull() {
        val newsUIModel = NewsUIModel("", null, null, NewsDateTime.of(2020, 1, 1,20,0), null, noContent, noDescription)
        assert(newsUIModel.getFormattedDescription() == noDescription)
    }

    @Test
    fun test_modelDisplaysDescriptionMessageWhenDescriptionFieldIsNotNull() {
        val newsUIModel = NewsUIModel("", "This is description", null, NewsDateTime.of(2020, 1, 1,20,0), "This is content", noContent, noDescription)
        assert(newsUIModel.getFormattedContent() != noDescription)
    }
}