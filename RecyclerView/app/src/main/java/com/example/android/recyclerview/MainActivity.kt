/*
 * Copyright (C) 2018 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.recyclerview

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

/**
 * Implements a basic RecyclerView that displays a list of generated words.
 * - Clicking an item marks it as clicked.
 * - Clicking the fab button adds a new word to the list.
 */
class MainActivity : AppCompatActivity() {

    private val mWordList = LinkedList<String>()

    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: WordListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val wordListSize = mWordList.size
            // Add a new word to the wordList.
            mWordList.addLast("+ Word $wordListSize")
            // Notify the adapter, that the data has changed.
            mRecyclerView!!.adapter!!.notifyItemInserted(wordListSize)
            // Scroll to the bottom.
            mRecyclerView!!.smoothScrollToPosition(wordListSize)
        }

        // Put initial data into the word list.
        for (i in 0..19) {
            mWordList.addLast("Word $i")
        }

        // Create recycler view.
        mRecyclerView = findViewById(R.id.recyclerview)
        // Create an adapter and supply the data to be displayed.
        mAdapter = WordListAdapter(this, mWordList)
        // Connect the adapter with the recycler view.
        mRecyclerView!!.adapter = mAdapter
        // Give the recycler view a default layout manager.
        mRecyclerView!!.layoutManager = LinearLayoutManager(this)
    }

    /**
     * Inflates the menu, and adds items to the action bar if it is present.
     *
     * @param menu Menu to inflate.
     * @return Returns true if the menu inflated.
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    /**
     * Handles app bar item clicks.
     *
     * @param item Item clicked.
     * @return True if one of the defined items was clicked.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        // This comment suppresses the Android Studio warning about simplifying
        // the return statements.

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }

    }
}
