INSERT INTO patient(name , gender , birth_date,email,blood_group)
VALUES
    ('kanhaiya','M','2004-08-22','kanhaiyakumarmailme@gmail.com','A_POSITIVE'),
    ('rajesh','M','2004-08-22','rajesh01@gmail.com','O_NEGATIVE'),
    ('manish','M','2006-07-08','manishJha@gmail.com','AB_POSITIVE'),
    ('shubham','M','2005-08-19','ShubhamJhamailme@gmail.com','AB_NEGATIVE'),
    ('mamta','F','1998-01-20','mamtayadav@gmail.com','O_POSITIVE');


INSERT  INTO  doctor(name , specialization , email)
VALUES
    ('Dr Rakesh Mehta' , 'Cardiology' ,'rakeshmehta@gmail.com'),
    ('Dr Manoj Kumar' , 'Dermatology' , 'manojkumar@gmail.com'),
    ('Dr Mukesh Singh' , 'Orthopedics','MukeshSingh@gmail.com');

INSERT INTO appointment (appointment_time, reason, doctor_id, patient_id)
VALUES
    ('2025-07-01 10:30:00', 'General Checkup', 1, 2),
    ('2025-07-02 11:00:00', 'Skin Rash', 2, 2),
    ('2025-07-03 09:45:00', 'Knee Pain', 3, 3),
    ('2025-07-04 14:00:00', 'Follow-up Visit', 1, 1),
    ('2025-07-05 16:15:00', 'Consultation', 1, 4),
    ('2025-07-06 08:30:00', 'Allergy Treatment', 2, 5);