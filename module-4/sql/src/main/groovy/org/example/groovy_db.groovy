import groovy.sql.Sql

import java.sql.ResultSetMetaData

connection = Sql.newInstance("jdbc:postgresql://localhost:5432/postgres",
        'postgres', 'changeme', "org.postgresql.Driver")

connection.execute '''
DROP TABLE IF EXISTS employees;
CREATE TABLE employees (
    id serial primary key,
    first_name text,
    last_name text
);
'''

class Employee {
    int id;
    String firstName
    String lastName
}

def employees = [['Andrey', 'Polyakov'], ['Petr', 'Polyakov'], ['Andrey', 'Ivanov']]
String stm = 'insert into employees(first_name, last_name) values (?, ?);'
employees.forEach {
    connection.execute stm, it
}


connection.execute stm, ['Andrey', 'Polyakov']
connection.execute stm, ['Petr', 'Polyakov']
connection.execute stm, ['Andrey', 'Ivanov']

//connection.execute('''
//insert into employees(first_name, second_name) values ('Andrey', 'Polyakov'), ('Petr', 'Polyakov'), ('Andrey', 'Ivanov')
//''')



connection.query("select first_name, last_name from employees", {resultSet ->
    ResultSetMetaData metaData = resultSet.metaData
    println "Table name is ${metaData.getTableName(1)}"
    for(i in 0..<metaData.columnCount) {
        println("${metaData.getColumnLabel(i+1)}-${metaData.getColumnTypeName(i+1)}")
    }


    while(resultSet.next()) {
        println([
                'firstName': resultSet.getString('first_name'),
                'lastName': resultSet.getString('last_name')
        ])
    }
})

result = connection.rows("select first_name, last_name from employees").each {
    ['firstName': it.first_name, 'lastName': it.last_name]
}
println result

