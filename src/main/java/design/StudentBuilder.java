package design;

public class StudentBuilder {
    private long id;
    private String name;

    public StudentBuilder id(long id) {
        this.id = id;
        return this;
    }

    public StudentBuilder name(String name) {
        this.name = name;
        return this;
    }

    public Student build() {
        return new Student(id, name);
    }

    public static void main(String[] args) {
        Student student = new StudentBuilder()
                .id(1L)
                .name("sangin")
                .build();

        Student student2 = Student.builder()
                .id(2L)
                .name("Sangin2")
                .build();

        System.out.println(student);
        System.out.println(student2);
    }
}

class Student {
    private long id;
    private String name;

    public Student(long id, String name) {
        this.id = id;
        this.name = name;
    }

    private Student(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private long id;
        private String name;

        private Builder() {
        }

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
