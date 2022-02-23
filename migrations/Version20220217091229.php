<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220217091229 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE collaborateur DROP prenom_collaborateur');
        $this->addSql('ALTER TABLE evenement ADD collaborateur1_id INT NOT NULL');
        $this->addSql('ALTER TABLE evenement ADD CONSTRAINT FK_B26681E6AA2E66A FOREIGN KEY (collaborateur1_id) REFERENCES collaborateur (id)');
        $this->addSql('CREATE INDEX IDX_B26681E6AA2E66A ON evenement (collaborateur1_id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE collaborateur ADD prenom_collaborateur VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE nom_collaborateur nom_collaborateur VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE role role VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`');
        $this->addSql('ALTER TABLE evenement DROP FOREIGN KEY FK_B26681E6AA2E66A');
        $this->addSql('DROP INDEX IDX_B26681E6AA2E66A ON evenement');
        $this->addSql('ALTER TABLE evenement DROP collaborateur1_id, CHANGE nom_evenement nom_evenement VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE description description VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE qr_code qr_code VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`');
    }
}
