package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {


    final Long ownerId = 1L;
    OwnerMapService ownerMapService;

    @BeforeEach
    void setUp() {

        ownerMapService = new OwnerMapService(new PetTypeMapService(),new PetMapService() );

        ownerMapService.save(Owner.builder().address("ssssssssss").lastName("jon").build());

    }

    @Test
    void findAll() {

        Set<Owner> owners = ownerMapService.findAll();

        assertEquals(1,owners.size());

    }

    @Test
    void findById() {

        Owner owner = ownerMapService.findById(ownerId);

        assertEquals(ownerId ,owner.getId());

    }

    @Test
    void saveExistingId() {
        Long id = 2L;
        Owner owner2 = Owner.builder().id(id).build();
        Owner savedOwner = ownerMapService.save(owner2);
        assertEquals(id , savedOwner.getId());
    }

    @Test
    void saveNoId() {

        Owner savedOwner = ownerMapService.save(Owner.builder().build());

        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(ownerId));
        assertEquals(0 , ownerMapService.findAll().size());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(ownerId);
        assertEquals(0 , ownerMapService.findAll().size());
    }

    @Test
    void findByLastName() {
        Owner jon = ownerMapService.findByLastName("jon");
        assertNotNull(jon);
        assertEquals(ownerId,jon.getId());

    }

    @Test
    void findByLastNameNotFound() {
        Owner jon = ownerMapService.findByLastName("foo");
        assertNull(jon);

    }
}