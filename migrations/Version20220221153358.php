<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220221153358 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE collaborateur (id INT AUTO_INCREMENT NOT NULL, nom_collaborateur VARCHAR(255) NOT NULL, prenom_collaborateur VARCHAR(255) NOT NULL, role VARCHAR(255) NOT NULL, numero_tel INT NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE evenement (id INT AUTO_INCREMENT NOT NULL, collaborateur1_id INT NOT NULL, nom_evenement VARCHAR(255) NOT NULL, description VARCHAR(255) NOT NULL, nombre_de_participants INT NOT NULL, qr_code VARCHAR(255) NOT NULL, billet INT NOT NULL, date_de_evenement DATE NOT NULL, INDEX IDX_B26681E6AA2E66A (collaborateur1_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE hh (id INT AUTO_INCREMENT NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE evenement ADD CONSTRAINT FK_B26681E6AA2E66A FOREIGN KEY (collaborateur1_id) REFERENCES collaborateur (id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE evenement DROP FOREIGN KEY FK_B26681E6AA2E66A');
        $this->addSql('DROP TABLE collaborateur');
        $this->addSql('DROP TABLE evenement');
        $this->addSql('DROP TABLE hh');
    }
}
