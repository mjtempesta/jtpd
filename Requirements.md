# Introduction #
JTPD projesinin hedefleri doğrultusunda kullanılması gereken teknolojilerin belirlenmesi, temel mimarisinin modellenmesi, ana amaçlar doğrultusunda hedefe yönelik geliştirmelerin yapılması.

# Teknolojik Gereksinimler #

  * Compile, Packaging ve CIS için MAVEN kullanılacak
  * Servlet Container olarak TOMCAT kullanılacak
  * JEE için SPRING entegrasyonu yapılacak
  * UI için JSF ve türev Teknolojileri kullanılacak (PrimeFaces v.b.)
  * ORM için Hibernate kullanılacak
  * JavaScript kodlamaları için standart paket belirlenip uygulanacak (Tartışılacak - JQuery, Prototype, Ex-GWT v.b.)
  * Continous Integration aracı olacak Hudson veya Maven Continuum
    1. http://continuum.apache.org/
    1. https://hudson.dev.java.net/
  * Maven Repository olarak NEXUS Repostiyory Manager http://nexus.sonatype.org/
  * JUnit ve JMock ile birim test yazımı


# Mimari Gereksinimler #

  * Generic Yapıların Oluşturulması
    1. GenericModel ve Model Sınıfları
    1. Her model için GenericDAO ve Arayüz yapıları
    1. Business Logic çağrımları için GenericService ve alt yapılarının oluşturulması
  * Container Managed Transaction yönetiminin uygulanması
  * Loglama Mekanizmasının oluşturulması
    1. Modüler Loglama'nın gerçekleştirilmesi
  * Fiziksel Modelin çıkarılması ve DB tarafına uygulanması

# Proje Gereksinimleri #
  * Hikaye 1 : Yazı ekleme işleminin gerçekleştirilmesi
    1. Task 1.1 : Fiziksel Modelinin Çıkarılması
    1. Task 1.2 : Logical Modelin Çıkarılması
    1. Task 1.3 : ORM konfigurasyonunun yapılması (Annotation veya hbm.xml)
    1. Task 1.4 : DAO Birim Testinin yazılması
    1. Task 1.5 : DAO sınıfının yazılması
    1. Task 1.6 : WEB arayüzünün gerçekleştirilmesi (dummy şekilde)
    1. Task 1.7 : View Katmanı için Service sınıfının birim testinin yazılması
    1. Task 1.8 : Service sınıfının yazılması

  * Modüllerden bağımsız authorization sisteminin (Role Yönetimi) gerçekleştirilmesi
    1. **READ** http://greybeardedgeek.net/wordpress/wp-content/uploads/2009/03/spring-security-whitepaper.pdf Permission-Based Authorization With Spring Security 2.0

Add your content here.  Format your content with:
  * Text in **bold** or _italic_
  * Headings, paragraphs, and lists
  * Automatic links to other wiki pages