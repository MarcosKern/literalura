package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Year;
import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    @Query("SELECT autor FROM Autor autor WHERE autor.anoNascimento <= :ano AND (autor.anoFalecimento IS NULL OR autor.anoFalecimento >= :ano)")
    List<Autor> findAutoresVivos(@Param("ano") Year ano);

    @Query("SELECT autor FROM Autor autor WHERE autor.anoNascimento = :ano AND (autor.anoFalecimento IS NULL OR autor.anoFalecimento >= :ano)")
    List<Autor> findAutoresVivosRefinado(@Param("ano") Year ano);

    @Query("SELECT autor FROM Autor autor WHERE autor.anoNascimento <= :ano AND autor.anoFalecimento = :ano")
    List<Autor> findAutoresPorAnoDeMorte(@Param("ano") Year ano);

    @Query("SELECT livro FROM Livro livro WHERE LOWER(livro.titulo) = LOWER(:titulo)")
    List<Livro> findByTitulo(String titulo);

    @Query("SELECT livro FROM Livro livro WHERE livro.idioma LIKE %:idioma%")
    List<Livro> findByIdioma(@Param("idioma") String idioma);

}
