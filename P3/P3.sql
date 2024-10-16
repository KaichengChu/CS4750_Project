-- Create Location table
CREATE TABLE Location(
    Location_id INT NOT NULL,
    Location_name VARCHAR(20),
    Location_description VARCHAR(60),
    capacity INT,
    CONSTRAINT Location_PK PRIMARY KEY (Location_id)
);
Alter TABLE [Location] ALTER COLUMN Location_Name VARCHAR(50);

-- Create User table
CREATE TABLE [User](
    User_id INT NOT NULL,
    Email VARCHAR(20),
    Last_name VARCHAR(15),
    First_name VARCHAR(15),
    CONSTRAINT User_PK PRIMARY KEY (User_id)
);

Alter TABLE [User] ALTER COLUMN Email VARCHAR(30);

-- Create Student table
CREATE TABLE Student(
    User_id INT NOT NULL CONSTRAINT
    Student_FK FOREIGN KEY (User_id) REFERENCES [User] (User_id),
    Student_year INT,
    Major VARCHAR(10),
    CONSTRAINT Student_PK PRIMARY KEY (User_id)
);

-- Create Faculty table
CREATE TABLE Faculty(
    User_id INT NOT NULL CONSTRAINT
    Faculty_FK FOREIGN KEY (User_id) REFERENCES [User] (User_id),
    Department VARCHAR(10),
    CONSTRAINT Faculty_PK PRIMARY KEY (User_id)
);

-- Create Organization table (fixed missing comma)
CREATE TABLE Organization(
    Organization_id INT NOT NULL,
    Organization_name VARCHAR(20),
    Organization_description VARCHAR(50),
    CONSTRAINT Organization_PK PRIMARY KEY (Organization_id)
);
ALTER TABLE Organization Alter COLUMN Organization_description VARCHAR(100);
ALTER TABLE Organization Alter COLUMN Organization_name VARCHAR(40);


-- Create Organizer table
CREATE TABLE Organizer(
    User_id INT NOT NULL CONSTRAINT Organizer_FK2 FOREIGN KEY (User_id) REFERENCES [User] (User_id),
    Organization_id INT CONSTRAINT
    Organizer_FK FOREIGN KEY (Organization_id)
    REFERENCES Organization (Organization_id),
    CONSTRAINT Organizer_PK PRIMARY KEY (User_id)
);

-- Create Participant table
CREATE TABLE Participant(
    User_id INT NOT NULL 
    CONSTRAINT Participant_FK FOREIGN KEY (User_id) REFERENCES [User] (User_id),
    CONSTRAINT Participant_PK PRIMARY KEY (User_id)
);

ALTER TABLE Participant ADD Total_attended_events INT DEFAULT 0;



-- Create Event table
CREATE TABLE Event(
    Event_id INT NOT NULL,
    Event_name VARCHAR(50),
    Event_type VARCHAR(15) CONSTRAINT
    Event_type_CHK CHECK (Event_type IN ('ENTERTAINMENT', 'ACADEMIC', 'BUSINESS', 'SOCIAL', 'CLUB', 'SEMINAR', 'CONFERENCE', 'OTHER') ),
    Start_time DATETIME,
    End_time DATETIME,
    Event_description VARCHAR(200),
    Location_id INT,
    picture VARBINARY(MAX),
    Organizer_id INT,
    CONSTRAINT Event_PK PRIMARY KEY (Event_id),
    CONSTRAINT Event_FK1 FOREIGN KEY (Location_id) REFERENCES [Location] (Location_id),
    CONSTRAINT Event_FK2 FOREIGN KEY (Organizer_id) REFERENCES Organizer (User_id)
);

-- Create Register table
CREATE TABLE Register(
    Event_id INT,
    User_id INT,
    is_present BIT,  -- Use BIT instead of BINARY for boolean values in MSSQL
    CONSTRAINT Register_PK PRIMARY KEY (Event_id, User_id),
    CONSTRAINT Register_FK1 FOREIGN KEY (Event_id) REFERENCES Event (Event_id),
    CONSTRAINT Register_FK2 FOREIGN KEY (User_id) REFERENCES [User] (User_id)
);

-- Create Creation table
CREATE TABLE Creation(
    Event_id INT CONSTRAINT Creation_FK FOREIGN KEY (Event_id) REFERENCES Event (Event_id),
    Create_date DATETIME,
    CONSTRAINT Creation_PK PRIMARY KEY (Event_id)
);

INSERT INTO Location (Location_id, Location_name, Location_description, capacity) 
VALUES 
(1, 'Main Hall', 'Large capacity room', 200),
(2, 'Conference Room 1', 'Medium-sized room', 50),
(3, 'Auditorium', 'Perfect for lectures and presentations', 300),
(4, 'Lab 1', 'Computer lab for workshops', 30),
(5, 'Library Hall', 'Ideal for academic events', 100),
(6, 'Outdoor Stage', 'Open-air event space', 500),
(7, 'Gymnasium', 'Sports and physical activity venue', 400),
(8, 'Board Room', 'Formal meeting room', 25),
(9, 'Exhibition Hall', 'Venue for fairs and exhibitions', 350),
(10, 'Cafeteria', 'Casual dining area', 150),
(11, 'Classroom A', 'Small classroom', 20),
(12, 'Classroom B', 'Small classroom', 25),
(13, 'Lecture Hall 1', 'Large lecture room', 100),
(14, 'Lecture Hall 2', 'Large lecture room', 120),
(15, 'Studio 1', 'Art studio', 40),
(16, 'Music Room', 'Room for music practice', 15),
(17, 'Dance Hall', 'Dance practice space', 60),
(18, 'Computer Lab 2', 'Advanced computing facilities', 40),
(19, 'Science Lab', 'Lab for experiments and research', 35),
(20, 'Seminar Room 1', 'Room for small seminars', 30),
(21, 'Seminar Room 2', 'Room for small seminars', 35),
(22, 'Sports Field', 'Outdoor field for sports', 600),
(23, 'Parking Lot', 'Space for vehicle parking', 800),
(24, 'Library Conference Room', 'Private study or meeting room', 10),
(25, 'Chapel', 'Religious event venue', 70),
(26, 'Art Gallery', 'Display space for exhibitions', 80),
(27, 'Film Screening Room', 'Small theater for film screenings', 90),
(28, 'Rooftop Garden', 'Open space for gatherings', 50),
(29, 'Faculty Lounge', 'Private lounge for faculty', 25),
(30, 'Event Space 30', 'Small venue for private events', 20);

INSERT INTO [User] (User_id, Email, Last_name, First_name) 
VALUES 
(1, 'john.doe@example.com', 'Doe', 'John'),
(2, 'jane.smith@example.com', 'Smith', 'Jane'),
(3, 'alice.brown@example.com', 'Brown', 'Alice'),
(4, 'bob.jones@example.com', 'Jones', 'Bob'),
(5, 'charlie.lee@example.com', 'Lee', 'Charlie'),
(6, 'sophia.miller@example.com', 'Miller', 'Sophia'),
(7, 'liam.davis@example.com', 'Davis', 'Liam'),
(8, 'emma.wilson@example.com', 'Wilson', 'Emma'),
(9, 'oliver.moore@example.com', 'Moore', 'Oliver'),
(10, 'ava.taylor@example.com', 'Taylor', 'Ava'),
(11, 'noah.anderson@example.com', 'Anderson', 'Noah'),
(12, 'mia.thomas@example.com', 'Thomas', 'Mia'),
(13, 'ethan.jackson@example.com', 'Jackson', 'Ethan'),
(14, 'amelia.harris@example.com', 'Harris', 'Amelia'),
(15, 'logan.martin@example.com', 'Martin', 'Logan'),
(16, 'isabella.thompson@example.com', 'Thompson', 'Isabella'),
(17, 'lucas.garcia@example.com', 'Garcia', 'Lucas'),
(18, 'harper.martinez@example.com', 'Martinez', 'Harper'),
(19, 'elijah.robinson@example.com', 'Robinson', 'Elijah'),
(20, 'lily.white@example.com', 'White', 'Lily'),
(21, 'james.perez@example.com', 'Perez', 'James'),
(22, 'ella.king@example.com', 'King', 'Ella'),
(23, 'mason.wright@example.com', 'Wright', 'Mason'),
(24, 'scarlett.scott@example.com', 'Scott', 'Scarlett'),
(25, 'jacob.evans@example.com', 'Evans', 'Jacob'),
(26, 'grace.morris@example.com', 'Morris', 'Grace'),
(27, 'aiden.walker@example.com', 'Walker', 'Aiden'),
(28, 'zoe.rivera@example.com', 'Rivera', 'Zoe'),
(29, 'samuel.carter@example.com', 'Carter', 'Samuel'),
(30, 'chloe.lopez@example.com', 'Lopez', 'Chloe');

INSERT INTO Student (User_id, Student_year, Major) 
VALUES
(1, 1, 'CS'),  -- First-year Computer Science student
(2, 2, 'BIO'), -- Second-year Biology student
(3, 3, 'MATH'), -- Third-year Mathematics student
(4, 4, 'PHYS'), -- Fourth-year Physics student
(5, 2, 'ENG'),  -- Second-year Engineering student
(6, 1, 'CHEM'), -- First-year Chemistry student
(7, 3, 'STAT'), -- Third-year Statistics student
(8, 4, 'HIST'), -- Fourth-year History student
(9, 1, 'CS'),   -- First-year Computer Science student
(10, 2, 'PHIL'), -- Second-year Philosophy student
(11, 4, 'CS'),  -- Fourth-year Computer Science student
(12, 3, 'BIO'), -- Third-year Biology student
(13, 2, 'ENG'), -- Second-year Engineering student
(14, 1, 'STAT'), -- First-year Statistics student
(15, 3, 'MATH'), -- Third-year Mathematics student
(16, 4, 'HIST'), -- Fourth-year History student
(17, 1, 'PHYS'), -- First-year Physics student
(18, 2, 'CHEM'), -- Second-year Chemistry student
(19, 3, 'PHIL'), -- Third-year Philosophy student
(20, 4, 'CS'),  -- Fourth-year Computer Science student
(21, 1, 'STAT'), -- First-year Statistics student
(22, 2, 'MATH'), -- Second-year Mathematics student
(23, 3, 'ENG'),  -- Third-year Engineering student
(24, 4, 'BIO'),  -- Fourth-year Biology student
(25, 2, 'CS'),  -- Second-year Computer Science student
(26, 1, 'PHYS'), -- First-year Physics student
(27, 4, 'CHEM'), -- Fourth-year Chemistry student
(28, 3, 'PHIL'), -- Third-year Philosophy student
(29, 2, 'STAT'), -- Second-year Statistics student
(30, 1, 'MATH'); -- First-year Mathematics student

INSERT INTO Faculty (User_id, Department) 
VALUES
(1, 'CS'),      -- Computer Science faculty
(2, 'BIO'),     -- Biology faculty
(3, 'MATH'),    -- Mathematics faculty
(4, 'PHYS'),    -- Physics faculty
(5, 'ENG'),     -- Engineering faculty
(6, 'CHEM'),    -- Chemistry faculty
(7, 'STAT'),    -- Statistics faculty
(8, 'HIST'),    -- History faculty
(9, 'PHIL'),    -- Philosophy faculty
(10, 'CS'),     -- Computer Science faculty
(11, 'BIO'),    -- Biology faculty
(12, 'ENG'),    -- Engineering faculty
(13, 'PHYS'),   -- Physics faculty
(14, 'CHEM'),   -- Chemistry faculty
(15, 'MATH'),   -- Mathematics faculty
(16, 'STAT'),   -- Statistics faculty
(17, 'HIST'),   -- History faculty
(18, 'PHIL'),   -- Philosophy faculty
(19, 'CS'),     -- Computer Science faculty
(20, 'BIO'),    -- Biology faculty
(21, 'ENG'),    -- Engineering faculty
(22, 'MATH'),   -- Mathematics faculty
(23, 'STAT'),   -- Statistics faculty
(24, 'PHYS'),   -- Physics faculty
(25, 'CHEM'),   -- Chemistry faculty
(26, 'HIST'),   -- History faculty
(27, 'PHIL'),   -- Philosophy faculty
(28, 'CS'),     -- Computer Science faculty
(29, 'ENG'),    -- Engineering faculty
(30, 'MATH');   -- Mathematics faculty

INSERT INTO Organization (Organization_id, Organization_name, Organization_description) 
VALUES
(1, 'Tech Society', 'A group dedicated to exploring technology advancements and innovations.'),
(2, 'Science Club', 'Promotes interest in scientific discovery and research.'),
(3, 'Math Enthusiasts', 'A club for students who love mathematics and problem-solving.'),
(4, 'History Buffs', 'A community for those passionate about history and culture.'),
(5, 'Literary Society', 'Encourages reading and writing among students.'),
(6, 'Environmental Club', 'Focuses on sustainability and environmental protection initiatives.'),
(7, 'Art Collective', 'A gathering of artists to share and collaborate on creative projects.'),
(8, 'Business Association', 'Prepares students for careers in business and entrepreneurship.'),
(9, 'Debate Team', 'A platform for students to hone their debating skills.'),
(10, 'Cultural Exchange', 'Fosters understanding and appreciation of diverse cultures.'),
(11, 'Health and Wellness', 'Promotes mental and physical well-being among students.'),
(12, 'Coding Club', 'A community for coding enthusiasts to collaborate on projects.'),
(13, 'Photography Society', 'Explores the art of photography and visual storytelling.'),
(14, 'Robotics Club', 'Builds and competes with robotic systems and technologies.'),
(15, 'Philosophy Society', 'Encourages discussions on philosophical ideas and theories.'),
(16, 'Music Ensemble', 'A group for musicians to practice and perform together.'),
(17, 'Film Society', 'Celebrates cinema through screenings and discussions.'),
(18, 'Volunteer Network', 'Connects students with local community service opportunities.'),
(19, 'Student Government', 'Represents the student body in decision-making processes.'),
(20, 'Gaming Guild', 'A group for video game enthusiasts to connect and play together.'),
(21, 'Innovation Lab', 'Encourages creative thinking and entrepreneurship.'),
(22, 'Women Empowerment', 'Supports women in leadership and personal development.'),
(23, 'Men Leadership', 'Focuses on male empowerment and leadership skills.'),
(24, 'Travel Club', 'Explores different cultures through travel and exploration.'),
(25, 'Anime Society', 'Celebrates Japanese animation and culture.'),
(26, 'Entrepreneurship Club', 'Supports aspiring entrepreneurs in their ventures.'),
(27, 'Culinary Club', 'Explores the art of cooking and food appreciation.'),
(28, 'Outdoor Adventures', 'Encourages outdoor activities and environmental appreciation.'),
(29, 'Academic Honor Society', 'Recognizes and honors academic excellence among students.'),
(30, 'Peer Mentoring Program', 'Connects experienced students with newcomers for guidance and support.');

INSERT INTO Organizer (User_id, Organization_id)
VALUES 
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10),
(11, 11),
(12, 12),
(13, 13),
(14, 14),
(15, 15),
(16, 16),
(17, 17),
(18, 18),
(19, 19),
(20, 20),
(21, 21),
(22, 22),
(23, 23),
(24, 24),
(25, 25),
(26, 26),
(27, 27),
(28, 28),
(29, 29),
(30, 30);


INSERT INTO Participant (User_id, Total_attended_events)
VALUES 
(1, 5),
(2, 3),
(3, 8),
(4, 2),
(5, 7),
(6, 0),
(7, 4),
(8, 1),
(9, 6),
(10, 3),
(11, 5),
(12, 9),
(13, 2),
(14, 7),
(15, 4),
(16, 3),
(17, 10),
(18, 6),
(19, 8),
(20, 5),
(21, 2),
(22, 4),
(23, 1),
(24, 7),
(25, 0),
(26, 9),
(27, 6),
(28, 3),
(29, 8),
(30, 5);


INSERT INTO Event (Event_id, Event_name, Event_type, Start_time, End_time, Event_description, Location_id, picture, Organizer_id) 
VALUES 
(1, 'Tech Expo', 'CONFERENCE', '2024-10-20 09:00', '2024-10-20 17:00', 'Technology showcase event', 1, NULL, 1),
(2, 'Math Symposium', 'SEMINAR', '2024-11-05 14:00', '2024-11-05 16:00', 'Mathematics lecture', 2, NULL, 2),
(3, 'CS Hackathon', 'SOCIAL', '2024-12-10 08:00', '2024-12-10 20:00', 'Programming contest', 3, NULL, 3),
(4, 'Biology Workshop', 'ACADEMIC', '2024-09-15 10:00', '2024-09-15 12:00', 'Hands-on biology workshop', 4, NULL, 4),
(5, 'Chemistry Talk', 'ACADEMIC', '2024-10-01 13:00', '2024-10-01 15:00', 'Talk by renowned chemist', 5, NULL, 5),
(6, 'Economics Forum', 'CONFERENCE', '2024-10-22 10:00', '2024-10-22 15:00', 'Discussion on economic theories', 6, NULL, 6),
(7, 'Engineering Meetup', 'SOCIAL', '2024-10-23 09:00', '2024-10-23 12:00', 'Meetup for engineers', 7, NULL, 7),
(8, 'Music Festival', 'SOCIAL', '2024-11-12 15:00', '2024-11-12 20:00', 'Live music performances', 8, NULL, 8),
(9, 'Film Screening', 'ENTERTAINMENT', '2024-11-15 18:00', '2024-11-15 21:00', 'Screening of a popular film', 9, NULL, 9),
(10, 'Art Exhibition', 'OTHER', '2024-11-20 12:00', '2024-11-20 18:00', 'Exhibition of modern art', 10, NULL, 10),
(11, 'Networking Event', 'SOCIAL', '2024-11-25 17:00', '2024-11-25 20:00', 'Networking for professionals', 11, NULL, 11),
(12, 'AI Conference', 'CONFERENCE', '2024-11-30 09:00', '2024-11-30 17:00', 'Advances in AI technology', 12, NULL, 12),
(13, 'Photography Workshop', 'OTHER', '2024-12-01 10:00', '2024-12-01 14:00', 'Workshop on photography skills', 13, NULL, 13),
(14, 'Science Fair', 'OTHER', '2024-12-05 09:00', '2024-12-05 15:00', 'Display of science projects', 14, NULL, 14),
(15, 'Literature Seminar', 'SEMINAR', '2024-12-10 10:00', '2024-12-10 12:00', 'Discussion on modern literature', 15, NULL, 15),
(16, 'Physics Colloquium', 'ACADEMIC', '2024-12-15 13:00', '2024-12-15 15:00', 'Colloquium on particle physics', 16, NULL, 16),
(17, 'Culinary Festival', 'SOCIAL', '2024-12-20 11:00', '2024-12-20 17:00', 'Celebration of international cuisines', 17, NULL, 17),
(18, 'Robotics Challenge', 'OTHER', '2024-12-25 09:00', '2024-12-25 19:00', 'Robotics design and challenge event', 18, NULL, 18),
(19, 'Business Startup Pitch', 'CONFERENCE', '2025-01-10 09:00', '2025-01-10 17:00', 'Startups pitch their ideas', 19, NULL, 19),
(20, 'Fitness Marathon', 'SOCIAL', '2025-01-20 07:00', '2025-01-20 11:00', 'Community fitness event', 20, NULL, 20),
(21, 'Game Development Workshop', 'OTHER', '2025-01-25 09:00', '2025-01-25 15:00', 'Game creation techniques', 21, NULL, 21),
(22, 'Architectural Tour', 'OTHER', '2025-02-01 10:00', '2025-02-01 13:00', 'Tour of architectural sites', 22, NULL, 22),
(23, 'Historical Lecture', 'ACADEMIC', '2025-02-05 14:00', '2025-02-05 16:00', 'Lecture on local history', 23, NULL, 23),
(24, 'Medical Symposium', 'SEMINAR', '2025-02-10 09:00', '2025-02-10 12:00', 'Symposium on new medical treatments', 24, NULL, 24),
(25, 'Entrepreneur Workshop', 'OTHER', '2025-02-15 13:00', '2025-02-15 17:00', 'Workshop for aspiring entrepreneurs', 25, NULL, 25),
(26, 'Environmental Conference', 'CONFERENCE', '2025-02-20 09:00', '2025-02-20 17:00', 'Discussion on climate change', 26, NULL, 26),
(27, 'VR Gaming Expo', 'OTHER', '2025-03-01 12:00', '2025-03-01 20:00', 'Expo on virtual reality gaming', 27, NULL, 27),
(28, 'Dance Performance', 'ENTERTAINMENT', '2025-03-05 18:00', '2025-03-05 21:00', 'Live dance performance', 28, NULL, 28),
(29, 'Fashion Show', 'SOCIAL', '2025-03-10 19:00', '2025-03-10 21:00', 'Showcasing new fashion trends', 29, NULL, 29),
(30, 'Event 30', 'OTHER', '2024-12-20 12:00', '2024-12-20 14:00', 'Miscellaneous event', 30, NULL, 30);

INSERT INTO Register (Event_id, User_id, is_present)
VALUES 
(1, 1, 1),
(2, 2, 0),
(3, 3, 1),
(4, 4, 1),
(5, 5, 0),
(6, 6, 1),
(7, 7, 0),
(8, 8, 1),
(9, 9, 0),
(10, 10, 1),
(11, 11, 0),
(12, 12, 1),
(13, 13, 0),
(14, 14, 1),
(15, 15, 0),
(16, 16, 1),
(17, 17, 0),
(18, 18, 1),
(19, 19, 0),
(20, 20, 1),
(21, 21, 0),
(22, 22, 1),
(23, 23, 0),
(24, 24, 1),
(25, 25, 0),
(26, 26, 1),
(27, 27, 0),
(28, 28, 1),
(29, 29, 0),
(30, 30, 1);

INSERT INTO Creation (Event_id, Create_date)
VALUES 
(1, '2024-09-01'),
(2, '2024-09-10'),
(3, '2024-09-15'),
(4, '2024-09-20'),
(5, '2024-09-25'),
(6, '2024-09-30'),
(7, '2024-10-05'),
(8, '2024-10-10'),
(9, '2024-10-15'),
(10, '2024-10-20'),
(11, '2024-10-25'),
(12, '2024-10-30'),
(13, '2024-11-04'),
(14, '2024-11-09'),
(15, '2024-11-14'),
(16, '2024-11-19'),
(17, '2024-11-24'),
(18, '2024-11-29'),
(19, '2024-12-04'),
(20, '2024-12-09'),
(21, '2024-12-14'),
(22, '2024-12-19'),
(23, '2024-12-24'),
(24, '2024-12-29'),
(25, '2025-01-03'),
(26, '2025-01-08'),
(27, '2025-01-13'),
(28, '2025-01-18'),
(29, '2025-01-23'),
(30, '2024-10-01');

SELECT Major, COUNT(*) AS Student_Count
FROM Student
GROUP BY Major;

SELECT Event_type, COUNT(*) AS Total_Events
FROM Event
GROUP BY Event_type;

SELECT MAX(capacity) AS Max_Capacity
FROM Location;

SELECT E.Event_name, COUNT(R.User_id) AS Total_Attendance
FROM Event E
LEFT JOIN Register R ON E.Event_id = R.Event_id
GROUP BY E.Event_name;

SELECT Department, COUNT(*) AS Faculty_Count
FROM Faculty
GROUP BY Department;

SELECT L.Location_name, COUNT(E.Event_id) AS Event_Count
FROM Location L
LEFT JOIN Event E ON L.Location_id = E.Location_id
GROUP BY L.Location_name;

SELECT S.Student_year, COUNT(R.User_id) AS Registered_Count
FROM Register R
JOIN Student S ON R.User_id = S.User_id
GROUP BY S.Student_year;

SELECT First_name, Last_name, Total_attended_events
FROM [User] u INNER JOIN Participant p
ON u.User_id = p.User_id 
WHERE p.Total_attended_events > 
(
    SELECT AVG(Total_attended_events)
    FROM Participant
);

SELECT Event_name, Event_type
FROM Event
WHERE Organizer_id IN (
    SELECT User_id
    FROM Organizer
    WHERE Organization_id = (
        SELECT Organization_id
        FROM Organizer
        WHERE User_id = 10
    )
);

SELECT User_id
FROM Participant
WHERE User_id IN (
    SELECT User_id
    FROM Register
    WHERE is_present = 0
);