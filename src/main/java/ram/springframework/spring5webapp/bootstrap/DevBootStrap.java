package ram.springframework.spring5webapp.bootstrap;


import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import ram.springframework.spring5webapp.model.Author;
import ram.springframework.spring5webapp.model.Book;
import ram.springframework.spring5webapp.model.Publisher;
import ram.springframework.spring5webapp.repositories.AuthorRepository;
import ram.springframework.spring5webapp.repositories.BookRepository;
import ram.springframework.spring5webapp.repositories.PublisherRepository;

@Component
public class DevBootStrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;


    public DevBootStrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    private void initData(){

        //Eric
        Author eric = new Author("Eric", "Evans");
        Publisher harper = new Publisher("Harper Collins","Baker street 32, 56234 London\n UK");
        Book ddd = new Book("Domain Driven Design","1234",harper);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        publisherRepository.save(harper);
        bookRepository.save(ddd);



        //Rod
        Author rod = new Author("Rod", "Johnson");
        Publisher worx = new Publisher("Worx","Big Ben avenue 475, 78541 Liverpool\n UK");
        Book noEJB = new Book("J2EE Development with no EJB","23344",worx);
        rod.getBooks().add(noEJB);
        authorRepository.save(rod);
        publisherRepository.save(worx);
        bookRepository.save(noEJB);

    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }



}
