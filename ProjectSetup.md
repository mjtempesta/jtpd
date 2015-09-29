# 1. Projenin SVN'den çekimi #

[SOURCE](http://code.google.com/p/jtpd/source/checkout) bölümündeki yönergelere uyularak projeyi makinanıza indirebilirsiniz. İndirilen kodları Eclipse üzerinde kullanımı için Eclipse üzerinde Package Explorer görüntüsü üzerinde sağ tıklayıp **import-->maven project" seçip açılan pencerede ana pom.xml'i içeren dizin gösterilir. Tanınan bütün pom.xml'ler seçilerek proje import edilir. Eklenen proje ve submoduller üzerine sağ tıklayıp**Team-->Share Project**diyerek SVN Repository'sine bağlantı kurulur.**

Yada Eclipse üzerinden SVN Repository tanımlayarak buradan proje seçilir ve checkout edilir.

# 2. Projenin çalıştırılması #

Şu anda jtpd.core modülü içerisinde Spring, Hibernate konfigurasyonları bulunmaktadır. Projenin Eclipse'e import edilmesi sırasında **jtpd-->project\_conf** dizini altında **shared launch** konfigurasyon dosyaları otomatik olarak Debug ve Run altına eklenir. Bu launch'lar ile jtpd.core uygulaması çalıştırılabilir.

# 3. Veritabanının Kurulması #

Windows sistemlerde  "windows\system32\drivers\etc\hosts" dosyasına ve  Linux sistemlerde "/etc/hosts" dosyasına  **127.0.0.1 db.jtpd.org** ifadesi eklenmelidir.

MySql Portu **3306** olmalıdır.

Uygulama için MySql Kullanıcısı eklenmeli:

mysql> CREATE USER 'jtpd'@'localhost' IDENTIFIED BY 'jtpd1234';
mysql> GRANT SELECT,INSERT,UPDATE,DELETE,EXECUTE ON jtpd.**TO 'jtpd'@'localhost';**

Liquibase için MySql Kullanıcısı eklenmeli (BKZ:jtpd.core/pom.xml):

mysql> CREATE USER 'liquibase'@'localhost' IDENTIFIED BY 'liquibase1234';
mysql> GRANT SELECT,INSERT,UPDATE,DELETE,CREATE,DROP,INDEX,ALTER,CREATE\_VIEW,CREATE\_ROUTINE,ALTER\_ROUTINE,EXECUTE ON jtpd.