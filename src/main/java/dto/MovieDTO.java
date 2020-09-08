/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Movie;

/**
 *
 * @author mattg
 */
public class MovieDTO {
    
    private String title;
    private int releaseYear;

    public MovieDTO(Movie movie) {
        this.title = movie.getTitle();
        this.releaseYear = movie.getReleaseYear();
    }

}


