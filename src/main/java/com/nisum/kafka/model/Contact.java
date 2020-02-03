package com.nisum.kafka.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = false)
public class Contact {
    String department;
    String firstName;
    String lastName;
    String primaryContact;
    String email;
    String phoneNumber;
    String role;
}
