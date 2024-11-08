import com.example.demo.Company
import com.example.demo.CompanyCustomizer;
import com.example.demo.Employee

def applyCustomizer(customizer){
    customizer.customize(it)
}

beans {
   companyCustomizer(CompanyCustomizer)

   company(Company) {bean ->
       bean.initMethod  = 'init'
       name = "Рога и копыта"
       customizer = companyCustomizer
   }

    employee(Employee) {
        firstName = 'Andrey'
        lastName = 'Polyakov'
        address = 'Улица Пушкина'
        employer = company
    }
}