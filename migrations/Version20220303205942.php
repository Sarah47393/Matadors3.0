<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220303205942 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE dons (id INT AUTO_INCREMENT NOT NULL, user_id INT DEFAULT NULL, num_carte INT NOT NULL, montant NUMERIC(10, 0) NOT NULL, INDEX IDX_E4F955FAA76ED395 (user_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE evenement (id INT AUTO_INCREMENT NOT NULL, visite_id INT DEFAULT NULL, nom_evenement VARCHAR(255) NOT NULL, description VARCHAR(255) NOT NULL, nombre_de_participants INT NOT NULL, qr_code VARCHAR(255) NOT NULL, billet INT NOT NULL, date_de_evenement DATE NOT NULL, collaborateur1_id INT NOT NULL, UNIQUE INDEX UNIQ_B26681EC1C5DC59 (visite_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE reservation (id INT AUTO_INCREMENT NOT NULL, users_id INT DEFAULT NULL, visite_id INT DEFAULT NULL, INDEX IDX_42C8495567B3B43D (users_id), INDEX IDX_42C84955C1C5DC59 (visite_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE user (id INT AUTO_INCREMENT NOT NULL, nom VARCHAR(255) NOT NULL, prenom VARCHAR(255) NOT NULL, cin INT NOT NULL, role VARCHAR(255) NOT NULL, access VARCHAR(255) NOT NULL, datenaissance DATE NOT NULL, password INT NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE visite (id INT AUTO_INCREMENT NOT NULL, evenement_id INT DEFAULT NULL, date DATE NOT NULL, description VARCHAR(255) NOT NULL, disponibilite INT NOT NULL, num_carte INT NOT NULL, montant NUMERIC(10, 0) NOT NULL, UNIQUE INDEX UNIQ_B09C8CBBFD02F13 (evenement_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE dons ADD CONSTRAINT FK_E4F955FAA76ED395 FOREIGN KEY (user_id) REFERENCES user (id)');
        $this->addSql('ALTER TABLE evenement ADD CONSTRAINT FK_B26681EC1C5DC59 FOREIGN KEY (visite_id) REFERENCES visite (id)');
        $this->addSql('ALTER TABLE reservation ADD CONSTRAINT FK_42C8495567B3B43D FOREIGN KEY (users_id) REFERENCES user (id)');
        $this->addSql('ALTER TABLE reservation ADD CONSTRAINT FK_42C84955C1C5DC59 FOREIGN KEY (visite_id) REFERENCES visite (id)');
        $this->addSql('ALTER TABLE visite ADD CONSTRAINT FK_B09C8CBBFD02F13 FOREIGN KEY (evenement_id) REFERENCES evenement (id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE visite DROP FOREIGN KEY FK_B09C8CBBFD02F13');
        $this->addSql('ALTER TABLE dons DROP FOREIGN KEY FK_E4F955FAA76ED395');
        $this->addSql('ALTER TABLE reservation DROP FOREIGN KEY FK_42C8495567B3B43D');
        $this->addSql('ALTER TABLE evenement DROP FOREIGN KEY FK_B26681EC1C5DC59');
        $this->addSql('ALTER TABLE reservation DROP FOREIGN KEY FK_42C84955C1C5DC59');
        $this->addSql('DROP TABLE dons');
        $this->addSql('DROP TABLE evenement');
        $this->addSql('DROP TABLE reservation');
        $this->addSql('DROP TABLE user');
        $this->addSql('DROP TABLE visite');
    }
}
