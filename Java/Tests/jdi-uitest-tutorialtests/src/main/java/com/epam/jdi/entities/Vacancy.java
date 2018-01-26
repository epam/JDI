package com.epam.jdi.entities;

import com.epam.commons.DataClass;



/**
 * Created by Roman_Iovlev on 11/22/2016.
 */
/*@AllArgsConstructor
@NoArgsConstructor*/
public class Vacancy extends DataClass {
   public String name;
   public String category;
   public String location;

   public Vacancy(){}

   public Vacancy(String position, String category, String location){
       this.name = position;
       this.category = category;
       this.location = location;
   }
}
