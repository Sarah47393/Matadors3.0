<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220222193815 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE user ADD password INT NOT NULL');
        $this->addSql('CREATE UNIQUE INDEX UNIQ_8D93D649ABE530DA ON user (cin)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE emplois CHANGE nom nom VARCHAR(10) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE prenom prenom VARCHAR(10) NOT NULL COLLATE `utf8mb4_unicode_ci`');
        $this->addSql('DROP INDEX UNIQ_8D93D649ABE530DA ON `user`');
        $this->addSql('ALTER TABLE `user` DROP password, CHANGE nom nom VARCHAR(10) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE prenom prenom VARCHAR(10) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE role role VARCHAR(10) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE access access VARCHAR(10) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE image image VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`');
    }
}
