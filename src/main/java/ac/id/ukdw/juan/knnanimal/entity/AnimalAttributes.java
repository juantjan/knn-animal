package ac.id.ukdw.juan.knnanimal.entity;

import lombok.Builder;
import lombok.Data;

/**
 * Created by Juan on 5/18/18.
 */
@Data
@Builder
public class AnimalAttributes {
    private String animal_name;
    private Integer hair;
    private Integer feathers;
    private Integer eggs;
    private Integer milk;
    private Integer airborne;
    private Integer aquatic;
    private Integer predator;
    private Integer toothed;
    private Integer backbone;
    private Integer breathes;
    private Integer venomous;
    private Integer fins;
    private Integer legs;
    private Integer tail;
    private Integer domestic;
    private Integer catsize;
    private String className;

}
