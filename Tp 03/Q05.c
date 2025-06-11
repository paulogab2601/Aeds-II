#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#define MAX_ENTRIES 1500
#define MAX_TEXT_LEN 256

typedef struct {
    char entry_id[MAX_TEXT_LEN];
    char category[MAX_TEXT_LEN];
    char program_title[MAX_TEXT_LEN];
    char director_name[MAX_TEXT_LEN];
    char principal_cast[MAX_TEXT_LEN];
    char origin_country[MAX_TEXT_LEN];
    char add_date[MAX_TEXT_LEN];
    int production_year;
    char maturity_rating[MAX_TEXT_LEN];
    char content_duration[MAX_TEXT_LEN];
    char genre_list[MAX_TEXT_LEN];
} EntertainmentEntry;

typedef struct Element {
    EntertainmentEntry* entry_data;
    struct Element* next_node;
} Element;

Element* head_of_list = NULL;

void assign_na_if_empty(char *text_field) {
    if (text_field[0] == '\0') {
        strcpy(text_field, "NaN");
    }
}

void arrange_cast_names(char *p_cast_field) {
    char *individual_names[30];
    int name_count = 0;
    char *token_ptr = strtok(p_cast_field, ",");

    while (token_ptr != NULL && name_count < 30) {
        while (*token_ptr == ' ') token_ptr++;
        individual_names[name_count++] = strdup(token_ptr);
        token_ptr = strtok(NULL, ",");
    }

    for (int i = 0; i < name_count - 1; i++) {
        for (int j = i + 1; j < name_count; j++) {
            if (strcmp(individual_names[i], individual_names[j]) > 0) {
                char *temp_name = individual_names[i];
                individual_names[i] = individual_names[j];
                individual_names[j] = temp_name;
            }
        }
    }

    p_cast_field[0] = '\0';
    for (int i = 0; i < name_count; i++) {
        strcat(p_cast_field, individual_names[i]);
        if (i < name_count - 1) strcat(p_cast_field, ", ");
        free(individual_names[i]);
    }
}

void sort_genre_categories(char *p_genre_field) {
    char *individual_genres[30];
    int genre_count = 0;
    char *token_ptr = strtok(p_genre_field, ",");

    while (token_ptr != NULL && genre_count < 30) {
        while (*token_ptr == ' ') token_ptr++;
        individual_genres[genre_count++] = strdup(token_ptr);
        token_ptr = strtok(NULL, ",");
    }

    for (int i = 0; i < genre_count - 1; i++) {
        for (int j = i + 1; j < genre_count; j++) {
            if (strcmp(individual_genres[i], individual_genres[j]) > 0) {
                char *temp_genre = individual_genres[i];
                individual_genres[i] = individual_genres[j];
                individual_genres[j] = temp_genre;
            }
        }
    }

    p_genre_field[0] = '\0';
    for (int i = 0; i < genre_count; i++) {
        strcat(p_genre_field, individual_genres[i]);
        if (i < genre_count - 1) strcat(p_genre_field, ", ");
        free(individual_genres[i]);
    }
}

void parse_entry_from_line(EntertainmentEntry *entry_to_fill, char *csv_line) {
    char *field_pointers[11];
    int current_field_idx = 0;
    int inside_quotes = 0;
    char temp_buffer[MAX_TEXT_LEN * 2];
    int buffer_pos = 0;
    char *line_iterator = csv_line;

    while (*line_iterator != '\0' && current_field_idx < 11) {
        if (*line_iterator == '"') {
            inside_quotes = !inside_quotes;
        } else if (*line_iterator == ',' && !inside_quotes) {
            temp_buffer[buffer_pos] = '\0';
            field_pointers[current_field_idx++] = strdup(temp_buffer);
            buffer_pos = 0;
        } else {
            temp_buffer[buffer_pos++] = *line_iterator;
        }
        line_iterator++;
    }

    temp_buffer[buffer_pos] = '\0';
    if (current_field_idx < 11) {
        field_pointers[current_field_idx++] = strdup(temp_buffer);
    }

    if (current_field_idx != 11) {
        memset(entry_to_fill, 0, sizeof(EntertainmentEntry));
        for (int i = 0; i < current_field_idx; i++) free(field_pointers[i]);
        return;
    }

    strcpy(entry_to_fill->entry_id, field_pointers[0]);           assign_na_if_empty(entry_to_fill->entry_id);
    strcpy(entry_to_fill->category, field_pointers[1]);           assign_na_if_empty(entry_to_fill->category);
    strcpy(entry_to_fill->program_title, field_pointers[2]);     assign_na_if_empty(entry_to_fill->program_title);
    strcpy(entry_to_fill->director_name, field_pointers[3]);     assign_na_if_empty(entry_to_fill->director_name);
    strcpy(entry_to_fill->principal_cast, field_pointers[4]);    assign_na_if_empty(entry_to_fill->principal_cast); arrange_cast_names(entry_to_fill->principal_cast);
    strcpy(entry_to_fill->origin_country, field_pointers[5]);    assign_na_if_empty(entry_to_fill->origin_country);
    strcpy(entry_to_fill->add_date, field_pointers[6]);          assign_na_if_empty(entry_to_fill->add_date);
    entry_to_fill->production_year = atoi(field_pointers[7]);     if (field_pointers[7][0] == '\0') entry_to_fill->production_year = 0;
    strcpy(entry_to_fill->maturity_rating, field_pointers[8]);    assign_na_if_empty(entry_to_fill->maturity_rating);
    strcpy(entry_to_fill->content_duration, field_pointers[9]);  assign_na_if_empty(entry_to_fill->content_duration);
    strcpy(entry_to_fill->genre_list, field_pointers[10]);       assign_na_if_empty(entry_to_fill->genre_list); sort_genre_categories(entry_to_fill->genre_list);

    for (int i = 0; i < 11; i++) {
        free(field_pointers[i]);
    }
}

void prepend_entry(EntertainmentEntry* new_entry) {
    Element* new_node = (Element*)malloc(sizeof(Element));
    new_node->entry_data = new_entry;
    new_node->next_node = head_of_list;
    head_of_list = new_node;
}

void append_entry(EntertainmentEntry* new_entry) {
    Element* new_node = (Element*)malloc(sizeof(Element));
    new_node->entry_data = new_entry;
    new_node->next_node = NULL;

    if (head_of_list == NULL) {
        head_of_list = new_node;
        return;
    }

    Element* current_node = head_of_list;
    while (current_node->next_node != NULL) {
        current_node = current_node->next_node;
    }
    current_node->next_node = new_node;
}

void insert_at_position(EntertainmentEntry* new_entry, int desired_pos) {
    if (desired_pos == 0) {
        return prepend_entry(new_entry);
    }

    Element* current_node = head_of_list;
    for (int i = 0; i < desired_pos - 1 && current_node != NULL; i++) {
        current_node = current_node->next_node;
    }

    if (current_node == NULL) {
        return;
    }

    Element* new_node = (Element*)malloc(sizeof(Element));
    new_node->entry_data = new_entry;
    new_node->next_node = current_node->next_node;
    current_node->next_node = new_node;
}

EntertainmentEntry* remove_from_beginning() {
    if (head_of_list == NULL) return NULL;

    Element* node_to_remove = head_of_list;
    head_of_list = head_of_list->next_node;
    EntertainmentEntry* removed_entry = node_to_remove->entry_data;
    free(node_to_remove);
    return removed_entry;
}

EntertainmentEntry* remove_from_end() {
    if (head_of_list == NULL) return NULL;

    if (head_of_list->next_node == NULL) {
        EntertainmentEntry* removed_entry = head_of_list->entry_data;
        free(head_of_list);
        head_of_list = NULL;
        return removed_entry;
    }

    Element* current_node = head_of_list;
    while (current_node->next_node->next_node != NULL) {
        current_node = current_node->next_node;
    }
    EntertainmentEntry* removed_entry = current_node->next_node->entry_data;
    free(current_node->next_node);
    current_node->next_node = NULL;
    return removed_entry;
}

EntertainmentEntry* remove_at_position(int target_pos) {
    if (target_pos == 0) return remove_from_beginning();

    Element* current_node = head_of_list;
    for (int i = 0; i < target_pos - 1 && current_node != NULL; i++) {
        current_node = current_node->next_node;
    }

    if (current_node == NULL || current_node->next_node == NULL) return NULL;

    Element* node_to_remove = current_node->next_node;
    current_node->next_node = node_to_remove->next_node;
    EntertainmentEntry* removed_entry = node_to_remove->entry_data;
    free(node_to_remove);
    return removed_entry;
}

void display_entry_details(EntertainmentEntry *current_entry) {
    char year_as_string[10];
    if (current_entry->production_year == 0)
        strcpy(year_as_string, "N/A");
    else
        sprintf(year_as_string, "%d", current_entry->production_year);

    printf("=> %s ## %s ## %s ## %s ## [%s] ## %s ## %s ## %s ## %s ## %s ## [%s] ##\n",
        current_entry->entry_id,
        current_entry->program_title,
        current_entry->category,
        current_entry->director_name,
        current_entry->principal_cast,
        current_entry->origin_country,
        current_entry->add_date,
        year_as_string,
        current_entry->maturity_rating,
        current_entry->content_duration,
        current_entry->genre_list
    );
}

void print_all_list_elements() {
    Element* current_node = head_of_list;
    while (current_node != NULL) {
        display_entry_details(current_node->entry_data);
        current_node = current_node->next_node;
    }
}

int main() {
    FILE *data_file = fopen("/tmp/disneyplus.csv", "r");
    if (data_file == NULL) {
        perror("Error opening the CSV data file");
        return 1;
    }

    EntertainmentEntry *all_entries = (EntertainmentEntry*)malloc(sizeof(EntertainmentEntry) * MAX_ENTRIES);
    int entry_count = 0;
    char line_buffer[1024];

    fgets(line_buffer, sizeof(line_buffer), data_file);

    while (fgets(line_buffer, sizeof(line_buffer), data_file) != NULL && entry_count < MAX_ENTRIES) {
        line_buffer[strcspn(line_buffer, "\n")] = 0;
        parse_entry_from_line(&all_entries[entry_count], line_buffer);
        if (all_entries[entry_count].entry_id[0] != '\0') {
            entry_count++;
        }
    }

    fclose(data_file);

    char user_input_command[MAX_TEXT_LEN];
    while (1) {
        fgets(user_input_command, sizeof(user_input_command), stdin);
        user_input_command[strcspn(user_input_command, "\n")] = 0;

        if (strcmp(user_input_command, "FIM") == 0) {
            break;
        }

        for (int i = 0; i < entry_count; i++) {
            if (strcmp(all_entries[i].entry_id, user_input_command) == 0) {
                append_entry(&all_entries[i]);
                break;
            }
        }
    }

    int total_commands;
    scanf("%d\n", &total_commands);

    for (int k = 0; k < total_commands; k++) {
        char command_line[100];
        fgets(command_line, sizeof(command_line), stdin);
        command_line[strcspn(command_line, "\n")] = 0;

        if (strncmp(command_line, "II ", 3) == 0) {
            char search_id[MAX_TEXT_LEN];
            sscanf(command_line + 3, "%s", search_id);
            for (int j = 0; j < entry_count; j++) {
                if (strcmp(all_entries[j].entry_id, search_id) == 0) {
                    prepend_entry(&all_entries[j]);
                    break;
                }
            }
        } else if (strncmp(command_line, "IF ", 3) == 0) {
            char search_id[MAX_TEXT_LEN];
            sscanf(command_line + 3, "%s", search_id);
            for (int j = 0; j < entry_count; j++) {
                if (strcmp(all_entries[j].entry_id, search_id) == 0) {
                    append_entry(&all_entries[j]);
                    break;
                }
            }
        } else if (strncmp(command_line, "I*", 2) == 0) {
            int position;
            char search_id[MAX_TEXT_LEN];
            sscanf(command_line + 2, "%d %s", &position, search_id);
            for (int j = 0; j < entry_count; j++) {
                if (strcmp(all_entries[j].entry_id, search_id) == 0) {
                    insert_at_position(&all_entries[j], position);
                    break;
                }
            }
        } else if (strcmp(command_line, "RI") == 0) {
            EntertainmentEntry* removed_item = remove_from_beginning();
            if (removed_item != NULL) printf("(R) %s\n", removed_item->program_title);
        } else if (strcmp(command_line, "RF") == 0) {
            EntertainmentEntry* removed_item = remove_from_end();
            if (removed_item != NULL) printf("(R) %s\n", removed_item->program_title);
        } else if (strncmp(command_line, "R*", 2) == 0) {
            int position;
            sscanf(command_line + 2, "%d", &position);
            EntertainmentEntry* removed_item = remove_at_position(position);
            if (removed_item != NULL) printf("(R) %s\n", removed_item->program_title);
        }
    }

    print_all_list_elements();

    free(all_entries);

    Element* current_node = head_of_list;
    while (current_node != NULL) {
        Element* next_node = current_node->next_node;
        free(current_node);
        current_node = next_node;
    }
    head_of_list = NULL;

    return 0;
}