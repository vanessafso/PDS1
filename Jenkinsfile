pipeline{

 agent any

  parameters {
    choice(choices: '-\n01/07/2019\n01/08/2019', description: 'Data de IDA', name: 'data_ida')
    choice(choices: '-\n10/07/2019\n15/07/2019', description: 'Data de VOLTA', name: 'data_volta')
    choice(choices: '-\n500$\n5000', description: 'VALOR', name: 'valor')
  }

  stages{

    stage ('Fazendo Checkout do Repositório'){
      steps {
        deleteDir()
        checkout scm
      }
    }
 
    stage ('Realizando teste de Funcionalidade'){
      when{
        expression { params.valor == '-' }
      }
      steps {
        sh 'Funcionalidade precisa ser preenchida'
      }
    }

      stage ('Realizando teste de Funcionalidade 2.0'){
      when{
        expression { params.valor != '-' }
      }
      steps {
        sh 'Funcionalidade Funcionando!!'
      }
    }


}
