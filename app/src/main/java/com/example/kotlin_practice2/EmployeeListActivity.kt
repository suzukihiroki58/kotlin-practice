package com.example.kotlin_practice2

import android.widget.Button
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.BufferedReader
import java.io.InputStreamReader

class EmployeeListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_list)

        val btnBackToMenu = findViewById<Button>(R.id.btnBackToMenu)
        btnBackToMenu.setOnClickListener {
            finish()
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewEmployees)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val employees = loadEmployeesFromCSV("社員一覧.csv")

        val adapter = EmployeeAdapter(employees)
        recyclerView.adapter = adapter
    }

    private fun loadEmployeesFromCSV(fileName: String): List<Employee> {
        val employees = mutableListOf<Employee>()
        val inputStream = assets.open(fileName)
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        var line: String?
        var id = 1

        while (bufferedReader.readLine().also { line = it } != null) {
            val tokens = line!!.split(",")
            if (tokens.size >= 2) {
                val employee = Employee(id, tokens[0], tokens[1])
                employees.add(employee)
                id++
            }
        }
        inputStream.close()
        return employees
    }

}
